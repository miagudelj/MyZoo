/**
 * view-controller for zoo.html
 *
 * M133: MyZoo
 *
 * @author Mia Gudelj
 */

/**
 * register listeners and load the animal data
 */
$(document).ready(
    function () {
        loadTiere();

        /**
         * listener for buttons within tierForm
         */
        $("#tierForm").on("click", "button", function () {
            if (confirm("Wollen Sie dieses Buch wirklich löschen?")) {
                deleteTier(this.value);
            }
        });
    }
);

/**
 * loads the tier from the webservice
 *
 */

function loadTiere() {

    $.ajax({
        url: "./resource/zoo/list",
        type: "GET",
        dataType: "json"
    })

    .done(showTiere)

    .fail(function (xhr, status, errorThrown) {
        if (xhr.status == 403) {
            window.location.href("./index.html");
        } else if (xhr.status == 404) {
            $("#message").text("keine Tiere vorhanden");
        }else {
            $("#message").text("Fehler beim Lesen der Tiere");
        }
    })
}


/**
 * shows all animals as a table
 *
 * @param tierData all animals as an array
 */
function showTiere(tierData) {

    $("#message").empty();
    $("#gehege > tbody").html("");

    var tableData = "";

    $.each(tierData, function (tierUUID, tier) {

        tableData += `<tr>`;
        tableData += `<td> ${tier.art}</td>`;
        tableData += `<td> ${tier.name}</td>`;
        tableData += `<td> ${tier.geburtsdatum}</td>`;
        tableData += `<td> ${tier.alter} Jahr(e)</td>`;
        tableData += `<td> ${tier.beine}</td>`;
        tableData += `<td> ${tier.fell ? "ja" : "nein"}</td>`;
        tableData += `<td> ${tier.zoo.zoo}</td>`;

        if (Cookies.get("userRole") == "admin") {
            tableData += "<td><a class='btn' role='button' href='./tieredit.html?tierUUID=" + tierUUID + "'>Bearbeiten</a></td>";
            tableData += "<td><button class='btn' type='button' id='delete_" + tierUUID + "' value='" + tierUUID + "'>Löschen</button></td>";

        } else {
            tableData += "<td><a href='./tieredit.html?tierUUID=" + tierUUID + "'>Ansehen</a></td>";

        }
        tableData += "</tr>";
    })
    $("#gehege > tbody").html(tableData);
}


/**
 * send delete request for a book
 * @param bookUUID
 */
function deleteTier(tierUUID) {
    $.ajax({
        url: "./resource/zoo/delete?tierUUID=" + tierUUID,
        dataType: "text",
        type: "DELETE",
    })

    .done(function (data) {
        loadTiere();
        $("#message").text("Tier gelöscht");

    })

    .fail(function (xhr, status, errorThrown) {
        $("#message").text("Fehler beim Löschen des Tiers");
    })
}
