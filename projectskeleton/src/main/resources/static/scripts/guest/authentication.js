
function login(usernameElement, passwordElement) {
    let username = usernameElement && usernameElement.value ? usernameElement.value : "";
    let password = passwordElement && passwordElement.value ? passwordElement.value : "";

    var fd = new FormData();
    fd.append( 'username', username);
    fd.append( 'password', password);

    $.ajax({
      url: API_ROOT + '/login',
      data: fd,
      processData: false,
      contentType: false,
      type: 'POST',
      success: function(data){
        sessionStorage.setItem(SESSION_STORAGE_LOGIN_TOKEN_NAME, username);
        window.location.replace(ROOT_PATH + "/pages/user/index.html");
      },
      statusCode: {
        401 : function() {
                alert("Invalid username or password!");
            }
        }
    });

}

function register() {
    // TODO:
}