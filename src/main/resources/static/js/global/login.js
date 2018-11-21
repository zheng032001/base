/**
 * 登录
 */
function doLogin(){
    var userId = $("#userId").val() ;
    var passWord = $("#passWord").val() ;
    var data = {
        userId:userId,
        passWord:passWord
    }
    $.ajax({
        url : "/global/user/doLogin" ,
        method : "POST",
        data : data,
        success : function(res){
            debugger ;
        },
        error : function(res){

        }
    }) ;
}