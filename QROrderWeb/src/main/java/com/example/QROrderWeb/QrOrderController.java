package com.example.QROrderWeb;

import jakarta.persistence.GeneratedValue;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.JsonPath;
import org.springframework.data.web.PageableDefault;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.repository.init.ResourceReader.Type.JSON;

@Controller
public class QrOrderController {

    @Autowired
    private MenueRepository menueRepository;
    private WatingListRepository watingListRepository;

    private ManagerRepository managerRepository;

    private BoardRepository boardRepository;
    private ShopeRepository shopeRepository;
    @GetMapping("/orderMenu")
    public String orderMenuList(@RequestParam Integer shnum, Model model){
        List<menue> menuList=menueRepository.findByShnum(shnum);
        List<String> metype=menueRepository.countMetype();
        String shName=shopeRepository.findById(shnum).get().getShname();
        model.addAttribute("menuList",menuList);
        model.addAttribute("metype",metype);
        model.addAttribute("shName",shName);
        return "orderMenu";
    }

    @PostMapping("/wating")
    public String wating(HttpServletRequest request, Model model){
        String[] idxme=request.getParameter("idxme").split(",");
        String[] mename=request.getParameter("mename").split(",");
        String[] prise=request.getParameter("prise").split(",");
        int shnum=Integer.parseInt(request.getParameter("shnum"));
        int watingNum=watingListRepository.getEndWating(shnum)+1;
        List<watingList> order=new ArrayList<watingList>();
        for(int i=0; i<idxme.length; i++){
            watingList watingList=new watingList();
            watingList.setIdxme(Integer.parseInt(idxme[i]));
            watingList.setMename(mename[i]);
            watingList.setPrise(Integer.parseInt(prise[i]));
            watingList.setWatingNum(watingNum);
            watingList.setShnum(shnum);
            watingListRepository.save(watingList);
            order.add(watingList);
        }
        model.addAttribute("orderList",order);
        model.addAttribute("shnum",shnum);
        model.addAttribute("watingNum",watingNum);
        orderHere(order, shnum);
        return "wating";
    }

    @MessageMapping("/waitingPersons")
    @SendTo("/PersonList")
    public int[] waitingPerson(){
        int[] watingPerson= watingListRepository.watingPerson();
        return watingPerson;
    }

    @PostMapping("/sellerJoin")
    public String sellerLogin(HttpServletRequest request, Model model){
        String shtell=(String)request.getAttribute("shtell");
        String shpass=(String)request.getAttribute("shpass");
        shops shope=shopeRepository.findByShtell(shtell);
        String erreMessage;
        String tothe;
        if(shope ==null){
            erreMessage="아이디를 찾을수 없습니다.";
            model.addAttribute("erreMessage",erreMessage);
            tothe= "login";
        }else if (!shope.getShpass().equals(shpass)){
            erreMessage="비밀번호가 틀립니다.";
            model.addAttribute("erreMessage",erreMessage);
            tothe= "login";
        }else {
            HttpSession session=request.getSession();
            session.setAttribute("shnum",shope.getShnum());
            tothe= "main";
        }
        return tothe;
    }

    @PostMapping("/managerJoin")
    public String managerLogin(@RequestParam String userId, String pass, Model model, HttpServletRequest request){
        Manager manager=managerRepository.findByUserId(userId);
        String erreMessage;
        String tothe;
        if(manager ==null){
            erreMessage="아이디를 찾을수 없습니다.";
            model.addAttribute("erreMessage",erreMessage);
            tothe= "login";
        }else if (!manager.getPass().equals(pass)){
            erreMessage="비밀번호가 틀립니다.";
            model.addAttribute("erreMessage",erreMessage);
            tothe= "login";
        }else {
            HttpSession session=request.getSession();
            session.setAttribute("userId",manager.getUserId());
            tothe= "mainBoard";
        }
        return tothe;
    }

    @GetMapping("/mainBoard")
    public String mainBoard(Model model, @PageableDefault(sort = "id", size = 10,direction = Sort.Direction.DESC) Pageable pageable){
        Page<Board> boardList=boardRepository.findAll(pageable);
        model.addAttribute("boardList",boardList);
        return "mainBorad";
    }

    @GetMapping("/managerBoard")
    public String managerBoard(Model model, @PageableDefault(sort = "id", size = 10,direction = Sort.Direction.DESC)
    Pageable pageable, HttpServletRequest request){
        HttpSession session=request.getSession();
        String manageridx=(String) session.getAttribute("userId");
        if(manageridx.isEmpty() || manageridx.equals("")){
            String erreMessage;
            erreMessage="로그인이 필요 합니다.";
            model.addAttribute("erreMessage",erreMessage);
            return "login";
        }
        Page<Board> boardList=boardRepository.findAll(pageable);
        int nowPage=boardList.getPageable().getPageNumber()+1;
        int nowSize=boardList.getSize();
        Long allCount=boardRepository.count();
        model.addAttribute("boardList",boardList);
        model.addAttribute("allCount",allCount);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("nowSize",nowSize);
        return "managerBoard";
    }

    @PostMapping("/boardAdd")
    public String boardAdd(HttpServletRequest request){
        HttpSession session=request.getSession();
        Board board=new Board();
        board.setTitle((String)request.getAttribute("title"));
        board.setContents((String)request.getAttribute("content"));
        board.setManagerId((String)session.getAttribute("userId"));
        boardRepository.save(board);
        return "managerBoard";
    }
    @GetMapping("/managerContent")
    public String boardRead(@RequestParam Integer idx, Model model){
        Optional<Board> board=boardRepository.findById(idx);
        model.addAttribute("board",board.get());
        return "managerContent";
    }
    @GetMapping("/mainContent")
    public String mainContent(@RequestParam Integer idx, Model model){
        Optional<Board> board=boardRepository.findById(idx);
        model.addAttribute("board",board.get());
        return "mainContent";
    }

    @PostMapping("/boardUpdate")
    public String boardUpdate(HttpServletRequest request, Model model){
        Integer idx=(Integer) request.getAttribute("idx");
        String title= (String) request.getAttribute("title");
        String contents=(String) request.getAttribute("contents");
        Board board=boardRepository.findById(idx).get();
        board.setTitle(title);
        board.setContents(contents);
        board.setWriteDay(LocalDate.now());
        model.addAttribute("board",board);
        return "managerContent";
    }

    @PostMapping("/boardDelete")
    public String boardDelete(HttpServletRequest request){
        Integer idx=(Integer) request.getAttribute("idx");
        Board board=boardRepository.findById(idx).get();
        boardRepository.delete(board);
        return "managerContent";
    }
    @GetMapping("/logOut")
    public String logOut(HttpServletRequest request){
        request.getSession().invalidate();
        return "login";
    }
    @PostMapping("/shopeMake")
    public String shopeMake(HttpServletRequest request){
        String  shname=(String) request.getAttribute("shame");
        String  shpass=(String) request.getAttribute("shpass");
        String  shtell=(String) request.getAttribute("shtell");
        String  shaddr=(String) request.getAttribute("shaddr");
        shops shope=new shops();
        shope.setShname(shname);
        shope.setShpass(shpass);
        shope.setShtell(shtell);
        shope.setShaddr(shaddr);
        shopeRepository.save(shope);
        return "login";
    }
    @GetMapping("/shopeManagement")
    public String shopeManagement(HttpServletRequest request, Model model){
        HttpSession session= request.getSession();
        Integer shnum=(Integer) session.getAttribute("shnum");
        List<menue> menuList=menueRepository.findByShnum(shnum);
        String shName=shopeRepository.findById(shnum).get().getShname();
        model.addAttribute("menuList",menuList);
        model.addAttribute("shName",shName);
        model.addAttribute("shnum",shnum);
        return "shopeManagement";
    }
    @GetMapping("/managementDelete/{idxme}")
    public String managementDelete(@PathVariable Integer idx){
        menueRepository.deleteById(idx);
        return "shopeManagement";
    }
    @PostMapping("/addMenue")
    public String addMenue(HttpServletRequest request) throws IOException {
        String mename=request.getAttribute("mename").toString();
        String type=request.getAttribute("mename").toString();
        Integer prise=(Integer) request.getAttribute("prise");
        Integer shnum=(Integer) request.getAttribute("shnum");
        MultipartFile img=(MultipartFile) request.getAttribute("img");
        Integer menum=menueRepository.findMaxmenue(shnum)+1;
        String location="C:\\programerpark\\portfolio\\QROrderWeb\\QROrderWeb\\src\\main\\resources\\static";
        String imgName= String.valueOf(shnum*1000+menum);
        File file=new File(location,imgName);
        img.transferTo(file);
        menue menue=new menue();
        menue.setMenum(menum);
        menue.setMename(mename);
        menue.setPrise(prise);
        menue.setShnum(shnum);
        menue.setMetype(type);
        menueRepository.save(menue);
        return "shopeManagement";
    }

    @SendTo("/orderHere/{shnum}")
    public List<watingList> orderHere(List<watingList> order, @DestinationVariable Integer shnum){
        return order;
    }

    @GetMapping("/open")
    public String open(HttpServletRequest request,Model model){
        HttpSession session=request.getSession();
        Integer shnum=(Integer) session.getAttribute("shnum");
        model.addAttribute("shnum",shnum);
        return "open";
    }
    @MessageMapping("/orderComplete")
    public void orderComplete(@Payload String watingNum){
        watingListRepository.updateComplete(watingNum);
        waitingPerson();
    }



}
