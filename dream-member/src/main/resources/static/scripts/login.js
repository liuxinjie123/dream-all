function doLogin() {
    $.post("/login/submit", $("#loginForm").serialize(),function(data){
        if (data.code == "000000") {
            var redirectPage = data.data.redirectPage;
            var indexPage = data.data.indexPage;
            if (redirectPage == null || redirectPage == "") {
                location.href = indexPage;
            } else {
                location.href = redirectPage;
            }
        } else {
            $("#loginError").val(data.msg);
        }
    });
}
$(function(){
    $("#login-button").click(function(){
        doLogin();
    });
});