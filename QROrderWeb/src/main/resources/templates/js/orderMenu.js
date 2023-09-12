const buttons = document.querySelectorAll(".contain");
const cart=document.getElementById("cart");

for( button of buttons){
    button.onclick = function (){

    const item={
        idxme : this.getAttribute('data-idxme'),
        mename : this.getAttribute('data-mename'),
        prise : this.getAttribute('data-prise')
    }

    const tr=document.createElement("tr");

        const inputIdxme=document.createElement("input");
        inputIdxme.setAttribute("name","idxme");
        inputIdxme.setAttribute("type","hidden");
        inputIdxme.setAttribute("value",item.idxme);

        const tdMename=document.createElement("td");
        const inputMename=document.createElement("input");
        inputMename.setAttribute("name","mename");
        inputMename.setAttribute("type","text");
        inputMename.setAttribute("value",item.mename);
        tdMename.appendChild(inputMename);

        const tdPrise=document.createElement("td");
        const inputPrise=document.createElement("input");
        inputPrise.setAttribute("name","prise");
        inputPrise.setAttribute("type","number");
        inputPrise.setAttribute("value",item.prise);
        tdPrise.appendChild(inputPrise);

        const tdDelelte=document.createElement("td");
        const buttonDelete=document.createElement("button");
        buttonDelete.setAttribute("type","button");
        buttonDelete.textContent="삭제";
        buttonDelete.addEventListener("click", function(){
            this.parentNode.parentNode.remove();
        })
        tdDelelte.appendChild(buttonDelete);

        tr.appendChild(inputIdxme);
        tr.appendChild(tdMename);
        tr.appendChild(tdPrise);
        tr.appendChild(tdDelelte);
        cart.appendChild(tr)
    }
}
document.getElementById("all").bind("click").onclick(()=>{
    document.getElementsByClassName("goods").setAttribute("display","block");
    this.bind("click");
});
let menubtns=document.getElementsByClassName("menubtn");
for(menubtn of menubtns){
    menubtns.onclick(()=>{
        let type=this.getAttribute("data-type");
        document.getElementsByClassName("goods").setAttribute("display","none");
        document.getElementsByClassName(type).setAttribute("display","block");
        menubtns.unbind("click");
        document.getElementById("all").unbind("click");
        this.bind("click");
    })
}
