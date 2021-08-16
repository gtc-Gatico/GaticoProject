{
    //矩形
    var Rect = function (x, y, width, height, color) {
        this.id = x + y + width + height + x * y * width * height;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color || "#000000";
        this.type = "Rect";
        Draw.elem[this.id] = this;
        return this;
    };
    //圆形（横坐标，纵坐标，起始角度，结束角度，顺false/逆true时针）
    var Arc = function (x, y, width, height, color, bangle, eangle, direction) {
        this.id = x + y + x * y;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bangle = bangle || 0;
        this.eangle = eangle || 2 * Math.PI;
        this.direction = direction | false;
        this.color = color || "#000000";
        this.type = "Arc";
        Draw.elem[this.id] = this;
        return this;

    };
    //只有边框的矩形
    var StrokeRect = function (x, y, width, height, color) {
        this.id = x + y + width + height + x * y * width * height;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color || "#000000";
        this.type = "StrokeRect";
        Draw.elem[this.id] = this;
        return this;
    };
    //只有边框的圆角矩形
    var RoundStrokeRect = function (x, y, width, height, radius, color) {
        this.id = x + y + width + height + x * y * width * height;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color || "#000000";
        this.radius = radius || 5;
        this.type = "RoundStrokeRect";
        Draw.elem[this.id] = this;
        return this;
    };
    //圆角矩形
    var RoundRect = function (x, y, width, height, radius, color) {
        this.id = x + y + width + height + x * y * width * height + radius;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radius = radius || 5;
        this.color = color || "#000000";
        this.type = "RoundRect";
        Draw.elem[this.id] = this;
        return this;
    };
    //文本
    var Text = function (x, y, text, color, font) {
        this.id = text,
        this.x = x;
        this.y = y;
        this.font = font || "14px",
        this.text = text;
        this.color = color || "#000000";
        this.type = "Text";
        Draw.elem[this.id] = this;
        return this;
    };
    //线条
    var Line = function (x1, y1, x2, y2, color, width, jt) {
        this.id = x1 + x2 + y1 + y2 + x1 * x2 * y1 * y2;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color || "#000000";
        this.width = width || 1;
        this.jt = jt;
        this.type = "Line";
        Draw.elem[this.id] = this;
        return this;
    };
    //菱形
    var Diamond = function (x, y, width, height, color) {
        this.id = x + y + width + height + x * y * width * height;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color || "#000000";
        this.type = "Diamond";
        Draw.elem[this.id] = this;
        return this;
    };
    //心形
    var Love = function (x, y, width, color, text, textColor) {
        this.id = x + y + width + x * y * width;
        this.x = x;
        this.y = y;
        this.width = width;
        this.text = text;
        this.color = color || "#000000";
        this.textColor = textColor || "#000000";
        Draw.elem[this.id] = this;
        this.type = "Love";
        return this;
    };
    //节点
    var Node = function (type, x, y, w, h, t, c, r) {
        this.id = x + y + w + h * x + y + w + h + t;
        this.x = x;
        this.y = y;
        this.type = type;
        this.text = t;
        this.width = w;
        this.height = h;
        this.radius = r;
        this.color = c || "#000000";
        Draw.elem[this.id] = this;
        return this;
    }
    //节点连线
    var nodeLine = function (node1, node2, text, width) {
        this.id = node1.id + node2.id;
        this.node1 = node1;
        this.node2 = node2;
        this.width = width || 2;
        this.text = text || "";
        Draw.elem[this.id] = this;
        return this;
    }

}
var Draw = {
    elem: {},
    context: null,
    canvas: null,
    isSelect: false,
    selectElem: null,
    kk: null,
    isdrop: false,
    backcolor: "#FFFFFF",
    shadow: false,
    shadowcolor: "rgba(0,0,0,0.5)",
    lineshadow: false,
    init: function (canvas) {
        var c = canvas.getContext("2d");
        c.beginPath();
        c.fillStyle = Draw.backcolor;
        c.fillRect(0, 0, c.width, c.height);
        c.fill();
        canvas.addEventListener("mousedown", function (even) {
            Draw.onMouseDown(even);
        });
        canvas.addEventListener("mousemove", function (even) {
            Draw.onMouseMove(even);
        });
        canvas.addEventListener("mouseup", function (even) {
            Draw.onMouseUp(even);
        });
        canvas.addEventListener("mouseover", function (even) {
            Draw.onMouseOver(even);
        });
        canvas.addEventListener("click", function (even) {
            Draw.onMouseClick(even);
        });
        Draw.context = c;
        Draw.canvas = canvas;
        return Draw;
    },
    // 矩形
    DrawRect: function (rr) {
        this.context.save();
        this.context.beginPath();
        this.context.fillStyle = rr.color;
        this.context.fillRect(rr.x, rr.y, rr.width, rr.height);
        this.context.closePath();
        this.context.fill();
        this.context.restore();
        this.context.save();
    },
    //圆角矩形
    DrawRoundRect: function (rr) {
        this.context.save();
        //开始路径规划
        this.context.beginPath();
        this.context.fillStyle = rr.color;
        //设置开始位置（不是顶点是为了不产生小尾巴）
        //为了不产生小尾巴做判断
        if (rr.width > 0) {
            this.context.moveTo(rr.x + rr.radius, rr.y);
        } else {
            this.context.moveTo(rr.x - rr.radius, rr.y);
        }
        //右上角
        this.context.arcTo(rr.x + rr.width, rr.y, rr.x + rr.width, rr.y + rr.height, rr.radius);
        //右下角(注意：由于arcTo的移动，当前点已经到：rr.x+rr.width，rr.y+rr.radius,所以不要再设置moveTo)
        this.context.arcTo(rr.x + rr.width, rr.y + rr.height, rr.x, rr.y + rr.height, rr.radius);
        //左下角
        this.context.arcTo(rr.x, rr.y + rr.height, rr.x, rr.y, rr.radius);
        //左上角
        if (rr.width > 0) {
            this.context.arcTo(rr.x, rr.y, rr.x + rr.radius, rr.y, rr.radius);
        } else {
            this.context.arcTo(rr.x, rr.y, rr.x - rr.radius, rr.y, rr.radius);
        }
        this.context.closePath();
        this.context.fill();
        this.context.restore();
    },
    //矩形边框
    DrawStrokeRect: function (rr) {
        this.context.save();
        this.context.beginPath();
        this.context.strokeStyle = rr.color;
        this.context.strokeRect(rr.x, rr.y, rr.width, rr.height);
        this.context.closePath();
        this.context.stroke();
        this.context.restore();
    },
    //圆角矩形边框
    DrawRoundStrokeRect: function (rr) {
        this.context.save();
        //开始路径规划
        this.context.beginPath();
        this.context.strokeStyle = rr.color;
        //设置开始位置（不是顶点是为了不产生小尾巴）
        //为了不产生小尾巴做判断
        if (rr.width > 0) {
            this.context.moveTo(rr.x + rr.radius, rr.y);
        } else {
            this.context.moveTo(rr.x - rr.radius, rr.y);
        }
        //右上角
        this.context.arcTo(rr.x + rr.width, rr.y, rr.x + rr.width, rr.y + rr.height, rr.radius);
        //右下角(注意：由于arcTo的移动，当前点已经到：rr.x+rr.width，rr.y+rr.radius,所以不要再设置moveTo)
        this.context.arcTo(rr.x + rr.width, rr.y + rr.height, rr.x, rr.y + rr.height, rr.radius);
        //左下角
        this.context.arcTo(rr.x, rr.y + rr.height, rr.x, rr.y, rr.radius);
        //左上角
        if (rr.width > 0) {
            this.context.arcTo(rr.x, rr.y, rr.x + rr.radius, rr.y, rr.radius);
        } else {
            this.context.arcTo(rr.x, rr.y, rr.x - rr.radius, rr.y, rr.radius);
        }
        this.context.closePath();
        this.context.stroke();
        this.context.restore();
    },
    //圆形
    DrawArc: function (rr) {
        this.context.save();
        this.context.beginPath();
        this.context.fillStyle = rr.color;
        this.context.arc(rr.x + rr.width / 2, rr.y + rr.height / 2, rr.width / 2, rr.bangle, rr.eangle, rr.direction);
        this.context.closePath();
        this.context.fill();
        this.context.restore();
    },
    //文本
    DrawText: function (rr) {
        this.context.beginPath();
        this.context.font = rr.font;
        this.context.fillStyle = rr.color;
        this.context.fillText(rr.text, rr.x, rr.y);
        this.context.closePath();
        this.context.fill();
        this.context.restore();
    },
    //箭头
    DrawLine: function (rr) {
        this.context.save();
        this.context.beginPath();
        this.context.lineWidth = rr.width;
        this.context.strokeStyle = rr.color;
        //画箭头
        if (rr.jt) {
            if (rr.y1 == rr.y2) {
                ///this.context.save();
                this.context.lineCap = "miter";
                this.context.moveTo(rr.x2, rr.y2);
                this.context.lineTo(rr.x2 - 5, rr.y2 - 5);
                this.context.moveTo(rr.x2, rr.y2);
                this.context.lineTo(rr.x2 - 5, rr.y2 + 5);
                //this.context.restore();
            }
            if (rr.x1 == rr.x2) {
                ///this.context.save();
                this.context.lineCap = "miter";
                this.context.moveTo(rr.x2, rr.y2);
                this.context.lineTo(rr.x2 - 5, rr.y2 - 5);
                this.context.moveTo(rr.x2, rr.y2);
                this.context.lineTo(rr.x2 + 5, rr.y2 - 5);
                //this.context.restore();
            }

        }
        //画线条
        this.context.lineCap = "round";
        this.context.moveTo(rr.x1, rr.y1);
        this.context.lineTo(rr.x2, rr.y2);
        this.context.stroke();
        this.context.closePath();
        this.context.restore();
    },
    //菱形
    DrawDiamond: function (rr) {
        this.context.save();
        this.context.beginPath();
        this.context.moveTo(rr.x, rr.y + rr.height / 2);//左中
        this.context.lineTo(rr.x + rr.width / 2, rr.y);//上中
        this.context.lineTo(rr.x + rr.width, rr.y + rr.height / 2);//右中
        this.context.lineTo(rr.x + rr.width / 2, rr.y + rr.height);//中下
        this.context.lineTo(rr.x, rr.y + rr.height / 2);//左中
        this.context.fillStyle = rr.color;
        this.context.closePath();
        this.context.fill();
        this.context.restore();
    },
    //心形
    DrawLove: function (nn) {
        var radius = nn.width / 2;
        var height = nn.width;
        this.context.save();
        this.context.beginPath();
        this.context.fillStyle = nn.color;
        this.context.arc(nn.x, nn.y, radius, 0, 3 / 4 * Math.PI, true);
        this.context.lineTo(nn.x + radius, nn.y + nn.width);
        this.context.arc(nn.x + nn.width, nn.y, radius, 9 / 4 * Math.PI, Math.PI, true);
        this.context.closePath();
        this.context.fill();
        if (nn.text != undefined) {
            this.context.save();
            var fw = this.context.measureText(nn.text).width;
            this.DrawText(nn.x + radius - fw / 2, nn.y + (height - radius) / 2, nn.text, bg3(), "bold 24px Arial");
            this.context.beginPath();
            this.context.font = "bold 24px Arial";
            this.context.fillStyle = nn.textColor;
            this.context.fillText(nn.text, nn.x + radius - fw / 2, nn.y + (nn.width - radius) / 2);
            this.context.closePath();
        }
        this.context.restore();
    },
    DrawNode: function (nn) {
        if (Draw.shadow) {
            this.context.shadowOffsetX = 5;
            this.context.shadowOffsetY = 5;
            this.context.shadowBlur = 4;
            this.context.shadowColor = Draw.shadowcolor;
        } else {
            this.context.shadowOffsetX = 0;
            this.context.shadowOffsetY = 0;
            this.context.shadowBlur = 0;
            this.context.shadowColor = Draw.backcolor;
        }
        if (nn.type == "Diamond") {//菱形
            this.context.save();
            this.context.beginPath();
            this.context.moveTo(nn.x, nn.y + nn.height / 2);//左中
            this.context.lineTo(nn.x + nn.width / 2, nn.y);//上中
            this.context.lineTo(nn.x + nn.width, nn.y + nn.height / 2);//右中
            this.context.lineTo(nn.x + nn.width / 2, nn.y + nn.height);//中下
            this.context.lineTo(nn.x, nn.y + nn.height / 2);//左中
            this.context.fillStyle = nn.color;
            this.context.closePath();
            this.context.fill();

            this.context.beginPath();
            this.context.fillStyle = "#FFFFFF";
            var fw = this.context.measureText(nn.text).width;
            this.context.fillText(nn.text, nn.x + nn.width / 2 - fw / 2, nn.y + (nn.width / 2) / 2);
            this.context.closePath();
            this.context.fill();
            this.context.restore();
        } else if (nn.type == "Love") {//心形
            Draw.DrawLove(nn);

        } else if (nn.type == "Arc") {//圆形
            Draw.DrawArc(nn);
            this.context.save();
            this.context.beginPath();
            this.context.fillStyle = nn.color;
            this.context.arc(nn.x + nn.width / 2, nn.y + nn.height / 2, nn.width / 2, 0, 2 * Math.PI, true);
            //this.context.lineTo(x+radius,y+width);
            //this.context.arc(x+width,y,radius,9/4*Math.PI,Math.PI,true);

            this.context.closePath();
            this.context.fill();

            this.context.beginPath();
            this.context.fillStyle = "#FFFFFF";
            var fw = this.context.measureText(nn.text).width;
            this.context.fillText(nn.text, nn.x + nn.width - nn.width / 2 - fw / 2, nn.y + nn.height / 2);
            this.context.closePath();
            this.context.fill();
            this.context.restore();
        } else if (nn.type == "RoundStrokeRect") {//圆角边框
            this.context.save();
            //开始路径规划
            this.context.beginPath();
            this.context.strokeStyle = nn.color;
            //设置开始位置（不是顶点是为了不产生小尾巴）
            //为了不产生小尾巴做判断
            if (nn.width > 0) {
                this.context.moveTo(nn.x + nn.radius, nn.y);
            } else {
                this.context.moveTo(nn.x - nn.radius, nn.y);
            }
            //右上角
            this.context.arcTo(nn.x + nn.width, nn.y, nn.x + nn.width, nn.y + nn.height, nn.radius);
            //右下角(注意：由于arcTo的移动，当前点已经到：rr.x+rr.width，rr.y+rr.radius,所以不要再设置moveTo)
            this.context.arcTo(nn.x + nn.width, nn.y + nn.height, nn.x, nn.y + nn.height, nn.radius);
            //左下角
            this.context.arcTo(nn.x, nn.y + nn.height, nn.x, nn.y, nn.radius);
            //左上角
            if (nn.width > 0) {
                this.context.arcTo(nn.x, nn.y, nn.x + nn.radius, nn.y, nn.radius);
            } else {
                this.context.arcTo(nn.x, rr.y, nn.x - nn.radius, nn.y, nn.radius);
            }
            this.context.closePath();
            this.context.stroke();

            this.context.beginPath();
            this.context.fillStyle = nn.color;
            var fw = this.context.measureText(nn.text).width;
            this.context.fillText(nn.text, nn.x + nn.width / 2 - fw / 2, nn.y + (nn.width / 2) / 2);
            this.context.closePath();
            this.context.fill();
            this.context.restore();
        } else if (nn.type == "StrokeRect") {//边框
            this.context.save();
            this.context.beginPath();
            this.context.strokeStyle = nn.color;
            this.context.strokeRect(nn.x, nn.y, nn.width, nn.height);
            this.context.closePath();
            this.context.stroke();

            this.context.beginPath();
            this.context.fillStyle = nn.color;
            var fw = this.context.measureText(nn.text).width;
            this.context.fillText(nn.text, nn.x + nn.width / 2 - fw / 2, nn.y + (nn.width / 2) / 2);
            this.context.closePath();
            this.context.fill();
            this.context.restore();

        } else if (nn.type == "RoundRect") {//圆角矩形
            this.context.save();
            //开始路径规划
            this.context.beginPath();
            this.context.fillStyle = nn.color;
            //设置开始位置（不是顶点是为了不产生小尾巴）
            //为了不产生小尾巴做判断
            if (nn.width > 0) {
                this.context.moveTo(nn.x + nn.radius, nn.y);
            } else {
                this.context.moveTo(nn.x - nn.radius, nn.y);
            }
            //右上角
            this.context.arcTo(nn.x + nn.width, nn.y, nn.x + nn.width, nn.y + nn.height, nn.radius);
            //右下角(注意：由于arcTo的移动，当前点已经到：rr.x+rr.width，rr.y+rr.radius,所以不要再设置moveTo)
            this.context.arcTo(nn.x + nn.width, nn.y + nn.height, nn.x, nn.y + nn.height, nn.radius);
            //左下角
            this.context.arcTo(nn.x, nn.y + nn.height, nn.x, nn.y, nn.radius);
            //左上角
            if (nn.width > 0) {
                this.context.arcTo(nn.x, nn.y, nn.x + nn.radius, nn.y, nn.radius);
            } else {
                this.context.arcTo(nn.x, nn.y, nn.x - nn.radius, nn.y, nn.radius);
            }
            this.context.closePath();
            this.context.fill();
            this.context.restore();

            this.context.beginPath();
            this.context.fillStyle = "#FFFFFF";
            var fw = this.context.measureText(nn.text).width;
            this.context.fillText(nn.text, nn.x + nn.width / 2 - fw / 2, nn.y + (nn.width / 2) / 2);
            this.context.closePath();
            this.context.fill();

            this.context.restore();
        } else if (nn.type == "Rect") {//矩形
            this.context.save();
            this.context.beginPath();
            this.context.fillStyle = nn.color;
            this.context.fillRect(nn.x, nn.y, nn.width, nn.height);

            this.context.closePath();
            this.context.fill();


            this.context.beginPath();
            this.context.fillStyle = "#FFFFFF";
            var fw = this.context.measureText(nn.text).width;
            this.context.fillText(nn.text, nn.x + nn.width / 2 - fw / 2, nn.y + (nn.width / 2) / 2);
            this.context.closePath();
            this.context.fill();
            this.context.restore();
        }
    },
    //画节点连线
    DrawNodeLine: function (nodeline) {
        if (Draw.lineshadow) {
            this.context.shadowOffsetX = 5;
            this.context.shadowOffsetY = 5;
            this.context.shadowBlur = 4;
            this.context.shadowColor = Draw.shadowcolor;
        } else {
            this.context.shadowOffsetX = 0;
            this.context.shadowOffsetY = 0;
            this.context.shadowBlur = 0;
            this.context.shadowColor = Draw.backcolor;
        }
        this.context.save();
        //this.context.globalCompositeOperation = "destination-over";//划线位置
        this.context.lineWidth = nodeline.width;
        this.context.strokeStyle = nodeline.node1.color;
        var x1 = nodeline.node1.x + nodeline.node1.width / 2;
        var y1 = nodeline.node1.y + nodeline.node1.height / 2;
        var x2 = nodeline.node2.x + nodeline.node2.width / 2;
        var y2 = nodeline.node2.y + nodeline.node2.height / 2;

        var x1l = x1 - nodeline.node1.width / 2;
        var x1r = x1 + nodeline.node1.width / 2;
        var y1t = y1 - nodeline.node1.height / 2;
        var y1b = y1 + nodeline.node1.height / 2;

        var x2l = x2 - nodeline.node2.width / 2;
        var x2r = x2 + nodeline.node2.width / 2;
        var y2t = y2 - nodeline.node2.height / 2;
        var y2b = y2 + nodeline.node2.height / 2;
        // //画箭头
        // if(nodeline.jt){
        //
        //     if(y1==y2){
        //         ///this.context.save();
        //         this.context.lineCap="miter";
        //         this.context.moveTo(x2,y2);
        //         this.context.lineTo(x2-5,y2-5);
        //         this.context.moveTo(x2,y2);
        //         this.context.lineTo(x2-5,y2+5);
        //         //this.context.restore();
        //     }
        //
        //     if(x1==x2){
        //         ///this.context.save();
        //         this.context.lineCap="miter";
        //         this.context.moveTo(x2,y2);
        //         this.context.lineTo(x2-5,y2-5);
        //         this.context.moveTo(x2,y2);
        //         this.context.lineTo(x2+5,y2-5);
        //         //this.context.restore();
        //     }
        //
        // }
        var ww = node2.width / 2 > node1.width / 2 ? node2.width / 2 : node1.width / 2;
        var hh = node2.height / 2 > node1.height / 2 ? node2.height / 2 : node1.height / 2;
        if (Math.abs(x2 - x1) <= ww) {//垂直相交的
            //垂直方向
            if (y2b <= y1t) {//node2在上面
                this.context.save();
                this.context.beginPath();
                this.context.moveTo(x1, y1t);
                this.context.lineTo(x1, y2b);
                this.context.lineTo(x1 - 5, y2b + 5);
                this.context.lineTo(x1, y2b);
                this.context.lineTo(x1 + 5, y2b + 5);
                this.context.stroke();
                Draw.drawlinetext(nodeline.text, x1, x1, y1t, y2b);
            } else {//node2在下
                this.context.save();
                this.context.beginPath();
                this.context.moveTo(x1, y1b);
                this.context.lineTo(x1, y2t);
                this.context.lineTo(x1 - 5, y2t - 5);
                this.context.lineTo(x1, y2t);
                this.context.lineTo(x1 + 5, y2t - 5);
                this.context.stroke();
                Draw.drawlinetext(nodeline.text, x1, x1, y2t, y1b);
            }
        } else if (Math.abs(y2 - y1) <= hh) {//水平相交
            if (x2l > x1l) {//node2在右边
                this.context.save();
                this.context.beginPath();
                this.context.moveTo(x1r, y1);
                this.context.lineTo(x2l, y1);
                this.context.lineTo(x2l - 5, y1 - 5);
                this.context.lineTo(x2l, y1);
                this.context.lineTo(x2l - 5, y1 + 5);
                this.context.stroke();
                Draw.drawlinetext(nodeline.text, x1r, x2l, y1);
            } else {//node2在左边
                this.context.save();
                this.context.beginPath();
                this.context.moveTo(x1l, y1);
                this.context.lineTo(x2r, y1);
                this.context.lineTo(x2r + 5, y1 + 5);
                this.context.lineTo(x2r, y1);
                this.context.lineTo(x2r + 5, y1 - 5);
                this.context.stroke();
                Draw.drawlinetext(nodeline.text, x2r, x1l, y1);

            }
        } else {
            if (x2 >= x1r && y2b <= y1) {//右上角
                this.context.save();
                this.context.beginPath();
                this.context.moveTo(x1r, y1);
                this.context.lineTo(x2, y1);
                this.context.lineTo(x2, y2b);
                this.context.lineTo(x2 - 5, y2b + 5);
                this.context.moveTo(x2, y2b);
                this.context.lineTo(x2 + 5, y2b + 5);
                this.context.stroke();
                Draw.drawlinetext(nodeline.text, x2, x2, y1, y2b);

            } else if (x2 <= x1l && y2 <= y1t) {//左上角
                this.context.save();
                this.context.beginPath();
                this.context.moveTo(x1l, y1);
                this.context.lineTo(x2, y1);
                this.context.lineTo(x2, y2b);
                this.context.lineTo(x2 - 5, y2b + 5);
                this.context.moveTo(x2, y2b);
                this.context.lineTo(x2 + 5, y2b + 5);
                this.context.stroke();
                Draw.drawlinetext(nodeline.text, x2, x2, y1, y2b);

            } else if (x2 <= x1l && y1 <= y2t) {//左下角
                this.context.save();
                this.context.beginPath();
                this.context.moveTo(x1l, y1);
                this.context.lineTo(x2, y1);
                this.context.lineTo(x2, y2t);
                this.context.lineTo(x2 - 5, y2t - 5);
                this.context.moveTo(x2, y2t);
                this.context.lineTo(x2 + 5, y2t - 5);
                this.context.stroke();
                Draw.drawlinetext(nodeline.text, x2, x2, y1, y2t);

            } else if (x2 >= x1r && y1b <= y2) {//右下角
                this.context.save();
                this.context.beginPath();
                this.context.moveTo(x1r, y1);
                this.context.lineTo(x2, y1);
                this.context.lineTo(x2, y2t);
                this.context.lineTo(x2 - 5, y2t - 5);
                this.context.moveTo(x2, y2t);
                this.context.lineTo(x2 + 5, y2t - 5);
                this.context.stroke();
                Draw.drawlinetext(nodeline.text, x2, x2, y1, y2t);

            }
        }

        this.context.restore();
    },
    drawlinetext: function (text, x1, x2, y1, y2) {
        this.context.save();
        this.context.beginPath();
        //this.context.font="bold 24px Arial";
        this.context.fillStyle = "#000000";
        var fw = this.context.measureText(text).width;
        if (x1 == x2) {//竖的线
            this.context.fillText(text, x1, y1 + ((y2 - y1) / 2));
        } else {//横的线
            var min = x1 < x2 ? x1 : x2;
            var max = x1 > x2 ? x1 : x2;
            this.context.fillText(text, (min + (max - min) / 2 - fw / 2), y1);
        }
        this.context.closePath();
        this.context.restore();
    },
    //坐标系
    DrawCoordinateSystem: function () {
        //网格
        this.context.save();
        this.context.strokeStyle = "lightgray";
        this.context.lineWidth = 0.5;
        this.context.shadowOffsetX = 0;
        this.context.shadowOffsetY = 0;
        this.context.shadowBlur = 0;
        this.context.shadowColor = Draw.backcolor;
        gridPP(this.context, 10, 10);
        this.context.restore();
        //坐标轴
        this.context.save();
        axesPP(this.context, 40, 10, 10, 10);
        this.context.restore();
    },
    //鼠标按下事件
    onMouseDown: function (even) {
        if (!Draw.isSelect) {
            //拖放
            Draw.isdrop = true;
            Draw.isSelect = true;
        } else {
            Draw.isSelect = false;
        }
    },
    //鼠标移动事件
    onMouseMove: function (even) {
        if (Draw.isdrop && Draw.isSelect) {
            var tt = Draw.selectElem;
            if (tt instanceof Line) {
                tt.x1 = even.layerX - (tt.x2 - tt.x1) / 2;
                tt.y1 = even.layerY - (tt.y2 - tt.y1) / 2;
                tt.x2 = even.layerX + (tt.x2 - tt.x1) / 2;
                tt.y2 = even.layerY + (tt.y2 - tt.y1) / 2;
            } else {
                tt.x = even.layerX - tt.width / 2;
                tt.y = even.layerY - tt.height / 2;
            }
            Draw.elem[tt.id] = tt;
        } else {
            for (i in this.elem) {
                var tx = this.elem[i];
                if (tx instanceof Line) {
                    if (even.layerX >= tx.x1 && even.layerX <= tx.x2) {
                        if (even.layerY > tx.y1 + tx.width && even.layerY <= tx.y2 + tx.width) {
                            if (!Draw.isSelect) {
                                Draw.selectElem = tx;
                                Draw.canvas.mouseover = Draw.onMouseOver(even);
                            }
                        } else {
                            // if(!Draw.isSelect) {
                            //     Draw.selectElem=null;
                            //     Draw.canvas.mouseover=null;
                            // }
                        }
                    }
                } else if (tx instanceof Text) {
                    var fw = Draw.context.measureText(tx.text).width;
                    if (even.layerX >= tx.x && even.layerX <= tx.x + fw) {
                        if (even.layerY > tx.y1 && even.layerY <= tx.y + 24) {
                            if (!Draw.isSelect) {
                                Draw.selectElem = tx;
                                Draw.canvas.mouseover = Draw.onMouseOver(even);
                            }
                        } else {
                            // if(!Draw.isSelect) {
                            //     Draw.selectElem=null;
                            //     Draw.canvas.mouseover=null;
                            // }
                        }
                    }
                } else {
                    if (even.layerX >= tx.x && even.layerX <= tx.width + tx.x) {
                        if (even.layerY > tx.y && even.layerY <= tx.y + tx.height) {
                            if (!Draw.isSelect) {
                                Draw.selectElem = tx;
                                Draw.canvas.mouseover = Draw.onMouseOver(even);
                            }
                        } else {
                            // if(!Draw.isSelect) {
                            //     Draw.selectElem=null;
                            //     Draw.canvas.mouseover=null;
                            // }
                        }
                    }
                }
            }
        }
        Draw.refulsh();
    },
    //鼠标松开事件
    onMouseUp: function (even) {

        Draw.isdrop = false;
        Draw.refulsh();
        Draw.isSelect = false;

    },
    //鼠标悬浮事件
    onMouseOver: function (even) {
        if (Draw.selectElem != null) {
            //document.getElementById("di").innerText=Draw.selectElem.id;
        }
    },
    //点击事件
    onMouseClick: function (even) {
        even.stopPropagation();
        Draw.selectElem = null;
        Draw.canvas.mouseover = null;
    },
    //刷新
    refulsh: function () {
        /*背景*/
        Draw.context.save();
        Draw.context.beginPath();
        Draw.context.fillStyle = Draw.backcolor;
        Draw.context.fillRect(0, 0, Draw.canvas.width, Draw.canvas.height);
        Draw.context.fill();
        Draw.context.closePath();
        Draw.context.restore();
        Draw.DrawCoordinateSystem();
        for (i in this.elem) {
            var tx = this.elem[i];
            if (tx instanceof nodeLine) {
                Draw.DrawObj(tx);
            }
        }
        for (i in this.elem) {
            var tx = this.elem[i];
            if (tx instanceof nodeLine) {
            } else {
                Draw.DrawObj(tx);
            }
            //Draw.DrawObj(tx);
        }

        /*选中元素边框*/
        if (/*Draw.isdrop||*/Draw.selectElem != null) {
            if (Draw.selectElem instanceof Line) {
                // Draw.context.save();
                // Draw.context.beginPath();
                // Draw.context.strokeStyle="red";
                // Draw.context.moveTo(Draw.selectElem.x1-2,Draw.selectElem.y1-2);
                // Draw.context.lineTo(Draw.selectElem.x2+4, Draw.selectElem.y2+4);
                // Draw.context.moveTo(Draw.selectElem.x1+2,Draw.selectElem.y1+2);
                // Draw.context.lineTo(Draw.selectElem.x2-4, Draw.selectElem.y2-4);
                // Draw.context.stroke();
                // //Draw.context.closePath();
                // Draw.context.restore();
            } else {
                Draw.context.save();
                Draw.context.beginPath();
                var color = "rgb(" + 255 - parseFloat(Draw.backcolor.r) + "," + 255 - parseFloat(Draw.backcolor.g) + "," + 255 - parseFloat(Draw.backcolor.r) + ")";
                Draw.context.strokeStyle = color;
                Draw.context.strokeRect(Draw.selectElem.x - 2, Draw.selectElem.y - 2, Draw.selectElem.width + 4, Draw.selectElem.height + 4);
                Draw.context.closePath();
                Draw.context.stroke();
                //Draw.context.beginPath();
                //Draw.context.fillStyle="pink";
                //Draw.context.fillRect(Draw.selectElem.x-2, Draw.selectElem.y+4+Draw.selectElem.height, Draw.selectElem.width+4, Draw.selectElem.height+4);
                //Draw.context.closePath();
                //this.context.fill();
                Draw.context.restore();
            }

        }
    },
    DrawObj: function (tx) {
        /*画元素*/
        /*文本*/
        if (tx instanceof Text) {
            Draw.DrawText(tx);
        }
        /*矩形*/
        else if (tx instanceof Rect) {
            Draw.DrawRect(tx);
        }
        /*圆角矩形*/
        else if (tx instanceof RoundRect) {
            Draw.DrawRoundRect(tx);
        }
        /*边框*/
        else if (tx instanceof StrokeRect) {
            Draw.DrawStrokeRect(tx);
        }
        /*圆角边框*/
        else if (tx instanceof RoundStrokeRect) {
            Draw.DrawRoundStrokeRect(tx);
        }
        /*圆形*/
        else if (tx instanceof Arc) {
            Draw.DrawArc(tx);
        }
        /*菱形*/
        else if (tx instanceof Diamond) {
            Draw.DrawDiamond(tx);
        }
        /*心形*/
        else if (tx instanceof Love) {
            Draw.DrawLove(tx);
        }
        /*直线*/
        else if (tx instanceof Line) {
            Draw.DrawLine(tx);
        } else if (tx instanceof Node) {
            Draw.DrawNode(tx);
        } else if (tx instanceof nodeLine) {
            Draw.DrawNodeLine(tx);
        }
    },

};
/////创建渐变对象
// function createRectLinearGradient(context, x, y, width, height) {
//     return context.createLinearGradient(x, y, x + width, y + height);
// }
//var gradient = context.createLinearGradient(30, 30, 70, 70);
//gradient.addColorStop(0, "white");
//gradient.addColorStop(1, "black");
///

//网格线
function gridPP(context, stepx, stepy) {
    //开始路径规划
    context.save();
    context.beginPath();
    //绘制x轴网格
    //注意：canvas在两个像素的边界处画线
    //由于定位机制，1px的线会变成2px
    //于是要+0.5
    for (var i = stepx + 0.5; i < context.canvas.width; i = i + stepx) {
        //开启路径
        //context.beginPath();
        context.moveTo(i, 0);
        context.lineTo(i, context.canvas.height);
    }
    //绘制y轴网格
    for (var i = stepy + 0.5; i < context.canvas.height; i = i + stepy) {
        //context.beginPath();
        context.moveTo(0, i);
        context.lineTo(context.canvas.width, i);
    }
    context.closePath();
    context.stroke();
    context.restore();
};

//坐标轴
function axesPP(context, axesMargin, htSpace, vtSpace, tickLong) {
    function mikuLoc(locX, locY) {
        this.x = locX;
        this.y = locY;
    }

    var originLoc = new mikuLoc(axesMargin, context.canvas.height - axesMargin);
    var axesW = context.canvas.width - (axesMargin * 2);
    var axesH = context.canvas.height - (axesMargin * 2);
    context.save();
    context.beginPath();
    context.strokeStyle = "#a2a2a2";
    context.lineWidth = 1;
    context.shadowOffsetX = 0;
    context.shadowOffsetY = 0;
    context.shadowBlur = 0;
    context.shadowColor = Draw.backcolor;
    //x,y轴规划
    horizontalAxisPP();
    verticalAxisPP();
    //x,y轴标签规划
    verticalAxisTicksPP();
    horizontalAxisTicksPP();
    context.stroke();
    context.closePath();
    context.restore();

    function horizontalAxisPP() {
        context.moveTo(originLoc.x, originLoc.y);
        context.lineTo(originLoc.x + axesW, originLoc.y);
    }

    function verticalAxisPP() {
        context.moveTo(originLoc.x, originLoc.y);
        context.lineTo(originLoc.x, originLoc.y - axesH);
    }

    function verticalAxisTicksPP() {
        var deltaX;
        //当前垂直tick的y轴坐标
        var nowTickY = originLoc.y - vtSpace;
        for (var i = 1; i <= (axesH / vtSpace); i++) {
            if (i % 5 === 0) {
                deltaX = tickLong;
            } else {
                deltaX = tickLong / 2;
            }
            //移动到当前的tick起点
            context.moveTo(originLoc.x - deltaX, nowTickY);
            context.lineTo(originLoc.x + deltaX, nowTickY);
            nowTickY = nowTickY - vtSpace;
        }
    }

    function horizontalAxisTicksPP() {
        var deltaY;
        var nowTickX = originLoc.x + htSpace;
        for (var i = 1; i <= (axesW / htSpace); i++) {
            if (i % 5 === 0) {
                deltaY = tickLong;
            } else {
                deltaY = tickLong / 2;
            }
            context.moveTo(nowTickX, originLoc.y + deltaY);
            context.lineTo(nowTickX, originLoc.y - deltaY);
            nowTickX = nowTickX + htSpace;
        }
    }
};

//辅助定位线
function guideWiresPP(context, x, y) {
    context.beginPath();
    drawHorizontalLine(y);
    drawVerticalLine(x);

    function drawHorizontalLine(y) {
        context.moveTo(0, y + 0.5);
        context.lineTo(context.canvas.width, y + 0.5);
    }

    function drawVerticalLine(x) {
        context.moveTo(x + 0.5, 0);
        context.lineTo(x + 0.5, context.canvas.height);
    }
}