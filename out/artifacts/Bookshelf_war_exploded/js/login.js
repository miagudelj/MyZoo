/**
 * Kurzbeschreibung
 *
 * @author  Mia Gudelj
 * @since   2020-04-10
 * @version 1.0
 */
$(document).ready(
    function () {

        $("#loginForm").submit(sendLogin);
        $("#logout").click(sendLogout);

    });

function sendLogin(form) {
    form.preventDefault();

    $.ajax({
        url: "./resource/user/login",
        type: "POST",
        dataType: "text",
        data: $("#loginForm").serialize()
    })

        .done(function (jsonData) {
            window.location.href = "./gehege.html";
        })

        .fail(function (xhr, status, errorThrown) {
            if (xhr.status == 404) {
                $("#message").text("Benutzername/Passwort unbekannt");
            } else {
                $("#message").text("Es ist ein Fehler aufgetreten");
            }
        })
}

function sendLogout() {

    $.ajax({
        url: "./resource/user/logout",
        type: "DELETE",
        dataType: "text"
    })

        .done(function (jsonData) {
            window.location.href = "./index.html"
        })

        .fail(function (xhr, status, errorThrown) {
            $("#message").text("Es ist ein Fehler aufgetreten");
        })
}