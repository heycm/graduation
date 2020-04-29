// 全局方法
export default {
    install(Vue)  {
        /**
         * 复制对象
         * @param {Object} obj 被复制对象
         * @return {Object} 对象副本
         */
        Vue.prototype.copyObj = (obj) => {
            return JSON.parse(JSON.stringify(obj))
        },
        /**
         * 清除对象的属性值
         * @param {Object} obj 被清除对象
         * @return {Object} 对象架子
         */
        Vue.prototype.clearObj = (obj) => {
            let o = Vue.prototype.copyObj(obj)
            return Vue.prototype.clearObjDeep(o)
        },
        Vue.prototype.clearObjDeep = (obj) => {
            // 判断obj是否是Object类型
            if(obj instanceof Object) {
                // 判断obj是否是Array类型
                if(Array.isArray(obj)) {
                    for (let key = 0; key < obj.length; key++) {
                        // 判断每一个元素是否是Object类型
                        if(obj[key] instanceof Object){
                            // 是，递归
                            obj[key] = Vue.prototype.clearObjDeep(obj[key])
                        } else {
                            // 不是，剪掉当前元素
                            obj.splice(key, 1);
                            key--;
                        }
                    }
                }
                // obj不是Array类型，是Object类型
                else {
                    // 遍历属性，递归
                    for(let key in obj) {
                        obj[key] = Vue.prototype.clearObjDeep(obj[key])
                    }
                }
                return obj
            }
            // obj是叶子
            else {
                return null
            }
        },
        /**
         * 格式化日期
         * @param {Date} time new Date()
         * @return {String} yyyy-MM-dd HH:mm:ss
         */
        Vue.prototype.dateFormat = (time) => {
            let year = time.getFullYear();
            let month = time.getMonth();
            let date = time.getDate();
            let hour = time.getHours();
            let min = time.getMinutes();
            let sec = time.getSeconds();

            month = month+1 >= 10 ? (month+1) : '0'+(month+1);
            date = date >= 10 ? date : '0'+date;
            hour = hour >= 10 ? hour : '0'+hour;
            min = min >= 10 ? min : '0'+min;
            sec = sec >= 10 ? sec : '0'+sec;

            let format = `${year}-${month}-${date} ${hour}:${min}:${sec}`;
            return format;

            // return `${time.getFullYear()}-${time.getMonth() + 1 >= 10 ? (time.getMonth() + 1) : '0' + (time.getMonth() + 1)}-${time.getDate() >= 10 ? time.getDate() : '0'+time.getDate()} ${time.getHours() >= 10 ? time.getHours() : '0'+time.getHours()}:${time.getMinutes()>=10?time.getMinutes() : '0'+time.getMinutes()}:${time.getSeconds() >= 10 ? time.getSeconds():'0' + time.getSeconds()}`;
        },
        /**
         * 将textarea的数据，换行转化为 <br>，空格转化为 &nbsp;
         * @param {String} str textarea输入框的数据
         * @return {String} 转换后的数据
         */
        Vue.prototype.transTextarea = (str) => {
            return str.replace(/\n|\r\n/g, "<br>").replace(/\s/g, "&nbsp;");
        },
        /**
         * 将字符串中的 <br> 解析为输入框需要的 \n，
         * 将 &nbsp; 解析为空格
         * @param {String} str 解析前的数据
         * @return {String} 解析后的数据
         */
        Vue.prototype.parseTextarea = (str) => {
            return str.replace(RegExp("<br>", "g"), "\n").replace(/&nbsp;/ig, " ");
        },
        /**
         * 设置当前登录用户信息
         * @param {Object} obj 当前登录用户信息
         */
        Vue.prototype.setUserInfo = (obj) => {
            localStorage.setItem("crim_current_user_info", JSON.stringify(obj))
        },
        /**
         * 获取当前登录用户信息
         */
        Vue.prototype.getUserInfo = () => {
            return JSON.parse(localStorage.getItem("crim_current_user_info"))
        },
        /**
         * 移除当前登录用户信息
         */
        Vue.prototype.removeUserInfo = () => {
            localStorage.removeItem("crim_current_user_info")
        }
    }
}