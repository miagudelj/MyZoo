/**
* view-controller for zoo.html
* 
* M133: MyZoo
*
* @author Mia Gudelj
*/

/**
* 
*/
$.urlParam = function(name) {
	var result = new RegExp('[/?&]' + name + '=([^&#]*)').exec(window.location.href);
	if (result == null) {
		return null;
	}
	return decodeURI(result[1]) || 0;
}

