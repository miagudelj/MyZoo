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
	function() {
		loadZoos();
		loadTier();

		/**
		 * listener for submitting the form
		 */
		$("#tiereditForm").submit(saveTier);

		/**
		 * listener for button [abbrechen], redirects to gehege
		 */
		$("#cancel").click(function () {
			window.location.href = "./gehege.html";
		});
	}
);

/**
 *  loads the data of this tier
 *
 */
function loadTier() {
	
	var tierUUID = $.urlParam("tierUUID");

	if (tierUUID !== null && tierUUID != -1) {
		$.ajax({
			url: "./resource/zoo/read?tierUUID=" + tierUUID,
			type: "GET",
			dataType: "json"
		})

		.done(showTier)

		.fail(function (xhr, status, errorThrown) {
			if (xhr.status == 403) {
				window.location.href = "./index.html";
			} else if (xhr.status == 404) {
				$("#message").text("kein Tier gefunden");
			} else {
				window.location.href = "./gehege.html";
			}
		})
	}
}


/**
 * shows the data of this tier
 * @param  tier  the tier data to be shown
 */
function showTier(tier) {
	$("#message").empty();

	$("#tierUUID").val(tier.tierUUID);
	$("#art").val(tier.art);
	$("#name").val(tier.name);
	$("#geburtsdatum").val(tier.geburtsdatum);
	$("#alter").val(tier.alter);
	$("#beine").val(tier.beine);
	$("#fell").prop("checked", tier.fell);
	$("#zoo").val(tier.zoo.zooUUID);

	if (Cookies.get("userRole") != "admin") {
		$("#art, #name, #geburtsdatum, #alter, #beine, #fell, #zoo").prop("readonly", true);
		$("#save, #reset").prop("disabled", true);
	}
}


/**
 * sends the book data to the webservice
 * @param form the form being submitted
 */
function saveTier(form) {
	form.preventDefault();
	var tierUUID = $("#tierUUID").val();

	var url = "./resource/zoo/";
	var type = "";

	if (tierUUID) {
		url += "update";
		type = "PUT";
	} else {
		url += "create";
		type = "POST";
	}

	$.ajax({
			url: url,
			dataType: "text",
			type: type,
			data: $("#tiereditForm").serialize()
		})

	.done(function (jsonData) {
		window.location.href = "./gehege.html";
	})

	.fail(function (xhr, status, errorThrown) {
		if (xhr.status == 404) {
			$("#message").text("Dieses Tier existiert nicht");
		} else {
			$("#message").text("Fehler beim Speichern des Tiers");
		}
	})
}

function loadZoos() {
	$.ajax({
			url: "./resource/gehege/list",
			dataType: "json",
			type: "GET"
		})
	.done(showZoos)

	.fail(function (xhr, status, errorThrown) {
		if (xhr.status == 404) {
			$("#message").text("Kein Zoo gefunden");
		} else {
			window.location.href = "./gehege.html";
		}
	})
}

function showZoos(zoos) {

	$.each(zoos, function (zooUUID, zoo) {
		$('#zoo').append($('<option>', {
			value: zoo.zooUUID,
			text : zoo.zoo
		}));
	});
}
