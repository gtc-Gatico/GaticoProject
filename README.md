# GaticoProject
个人的测试代码
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<canvas id="can" width="400px" height="400px" style="background-color: #000000;display:block;margin:0px auto"></canvas>
<script>
    var sn=[02,01],dz=03,fx=1,n,ctx=document.getElementById("can").getContext("2d");
    function  draw(t,c) {
        ctx.fillStyle=c;
        ctx.fillRect(t%20*20+1,~~(t/20)*20+1,18,18);
    }
    document.onkeydown=function(e){
        fx=sn[1]-sn[0]==(n=[-1,-20,1,20][(e||event).keyCode-37]||fx)?fx:n;
        console.log(fx);
    }
    !function(){
        sn.unshift(n=sn[0]+fx);
        if(sn.indexOf(n,1)>0||n<0||n>399||fx==1&&n%20==0||fx==-1&&n%20==19){
            return alert("GAME OVER");
        }
        draw(n,"Lime");
        if(n==dz){
            while(sn.indexOf(dz=~~(Math.random()*400))>=0);
                draw(dz,"Yellow")
        }else{
            draw(sn.pop(),"Black");
        }
        setTimeout(arguments.callee,130);
    }();
</script>
</body>
</html>