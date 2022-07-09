function $(id) {
    return document.getElementById(id);
}

window.onload = function () {
    var codeStatus = $("codeStatus");
    console.log(codeStatus.value);
    if (codeStatus.value === "0") {
        var codeStatusSpan = $("codeStatusSpan");
        codeStatusSpan.style.visibility = "visible";
    }
}

function check() {

    // 用户名应为6~16位数字和字母组成
    var uname = $("unameTxt").value;
    var unameSpan = $("unameSpan");

    var reg = /^[0-9A-Za-z]{6,16}$/;
    if (!reg.test(uname)) {
        unameSpan.style.visibility = "visible";
        return false;
    }
    unameSpan.style.visibility = "hidden";

    // 密码的长度至少为8位
    var pwd = $("pwdTxt").value;
    var pwdSpan = $("pwdSpan");

    reg = /^.{8,}$/;
    if (!reg.test(pwd)) {
        pwdSpan.style.visibility = "visible";
        return false;
    }
    pwdSpan.style.visibility = "hidden";

    // 密码两次输入不一致
    var pwd2 = $("pwdTxt2").value;
    var pwdSpan2 = $("pwdSpan2");

    if (pwd !== pwd2) {
        pwdSpan2.style.visibility = "visible";
        return false;
    }
    pwdSpan2.style.visibility = "hidden";

    // 密码的长度至少为8位
    var email = $("emailTxt").value;
    var emailSpan = $("emailSpan");

    reg = /^[a-zA-Z0-9_\.-]+@([a-zA-Z0-9-]+[\.]{1})+[a-zA-Z]+$/;
    if (!reg.test(email)) {
        emailSpan.style.visibility = "visible";
        return false;
    }
    emailSpan.style.visibility = "hidden";

    return true;
}