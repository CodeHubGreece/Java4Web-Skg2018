function logout() {
    sessionStorage.removeItem(SESSION_STORAGE_LOGIN_TOKEN_NAME);
    window.location.replace(ROOT_PATH + "/logout")
}

function populateDataTable(books) {
    
    $("#books").append("<tbody>");
    jQuery.each(books, function(i,book) {
        $("#books").append("<tr id='bookRow" + book.id + "'><td>" + book.id + "</td><td>" + book.title + "</td></tr>");
     });
     $("#books").append("</tbody>");

     $("#books tr").click(function() {
        loadBook($(this).children("td").html());
     });
}

function loadBook(id) {
    $.ajax({
        url: API_ROOT + "/books/" + id
    }).then(function(book) {
       $("input[name=id]").val(book.id);
       $("input[name=title]").val(book.title);
       $("input[name=isbn]").val(book.isbn);
    });
};

$(document).ready(function() {
    $.ajax({
        url: API_ROOT + "/books"
    }).then(function(books) {
        populateDataTable(books);
    });

    $("#saveButton").on('click', function(event){
        event.preventDefault();
        alert("To be done...");
    });

    $("#deleteButton").on('click', function(event){
        event.preventDefault();
        let bookId = $("input[name=id]").val();
        $.ajax({
            url: API_ROOT + "/books/" + bookId,
            type : "DELETE",
            dataType : 'json',
            contentType: 'application/json',
                success : function(result) {
                    $("#bookRow" + bookId).remove();
                    $("input[name=id]").val("");
                    $("input[name=title]").val("");
                    $("input[name=isbn]").val("");
                },
                error: function(xhr, resp, text) {
                    console.log(xhr, resp, text);
                    alert("Could not delete book!");
                 }
         })

    });
});
