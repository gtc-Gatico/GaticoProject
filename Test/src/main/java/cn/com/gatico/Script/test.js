// print(file.write("F://text.txt", "你好\n", false));
// print(file.read("F://text.txt"));
var a = 1;
print(++a);
var res = http.get("https://gatico.com.cn");
print(res);
var index = res.indexOf("动态时间");
print(index);
print(res.replace("时间","控件"));

function c(){
    print(a<<1)
}
c();
thread.start(http.get("https://gatico.com.cn"),function(data){
    print(data)
});


