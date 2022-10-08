// print(file.write("F://text.txt", "你好\n", false));
print(file.read("D:\\Project\\Java\\GaticoProject\\README.md"));
var a = 1;
print(++a);
var res = http.get("http://gatico.com.cn","");
print(res);
var index = res.indexOf("动态时间");
print(index);
print(res.replace("时间","控件"));

function c(){
    print(a<<1)
}
c();
print("开始里")
thread.start(file.read("D:\\Project\\Java\\GaticoProject\\README.md"),function(data){
    print(1234);
    print(data);
    print(new Date().getTime())
    for (var i = 0; i < 1000; i++) {
        print(i);
    }
    print(new Date().getTime())
});
print("结束了")
print()
