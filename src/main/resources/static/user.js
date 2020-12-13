//导航需要加载模块
layui.use('element', function(){
    var element = layui.element;

});





//不带参数跳转链接
function index(id){
    var temp = document.createElement("form");
    <!-- 跳转连接-->
    temp.action = "/index";
    temp.method = "post";
    temp.style.display = "none";
    var opt = document.createElement("input");
    opt.name = "id";
    opt.value = id;
    if (id == null)
        opt.value = 1;
    temp.appendChild(opt);



    document.body.appendChild(temp);
    temp.submit();
    return temp;
}


//带参数跳转链接
function activeBoard(current) {
    var temp = document.createElement("form");
    temp.action = "/active";
    temp.method = "post";
    temp.style.display = "none";
    var opt = document.createElement("input");
    opt.name = "current";
    opt.value = current;
    temp.appendChild(opt);
    document.body.appendChild(temp);
    temp.submit();
    return temp;
}



//根据状态选择div是否隐藏
function stateChange() {

    var text = document.getElementById("text");
    var visualization = document.getElementById("visualization");
    var button = document.getElementById("stateChange");

    if (text.style.display == "none"){
        visualization.style.display="none";
        text.style.display = "";
        button.innerHTML = "可视化显示";
    }
    else{
        text.style.display = "none";
        visualization.style.display="";
        button.innerHTML = "数据显示";
    }




}