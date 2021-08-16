(function () {
    this.$ = function (e) {
        let elements = document.querySelectorAll(e);
        return elements.length > 1 ? elements : elements[0];
    };

    this.config = {sleepDelay: 0, delay: 0,interval:0};
    this.defaultConfig = {
        delay: 0,
    };

    this.setDefaultConfig = function (config) {
        this.config.delay = config.delay | this.defaultConfig.delay;
    };

    let stack = [];
    this.getStack = function () {
        return stack;
    };

    this.start = function () {
        this.config.interval=this.run();
        return this;
    };

    this.next = function (func) {
        stack.push(func);
    };

    this.end = function () {
        stack=[];
        clearInterval(this.config.interval);
    };

    this.sleep = function (delay) {
        stack.push(function () {
            this.config.sleepDelay = new Date().getTime() + delay;
        });
    };

    this.run = function () {
      return setInterval(function () {
            if (new Date().getTime() - this.config.sleepDelay > 0) {
                let func = stack.pop();
                if (func !== null && func !== undefined) {
                    func.call();
                }
            }
        }, this.config.delay);

    };
    for (let key in Object.keys(this)) {
        window[key] = this[key];
    }
}());

