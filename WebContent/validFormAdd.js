/**
 * used in addComputer.jsp and editComputer.jsp
 */
	function validForm()
	{
		if (($("#nameInput").val()!="")&&($("#introducedInput").val()!="")&&($("#discontinuedInput").val()!="")) {
			if ((validDate($("#introducedInput").val()))&&(validDate($("#discontinuedInput").val()))) {
				$("#formAdd").submit();
			}
			else {
				$("#message").html("Au moins une des dates ne correspond pas au format n&eacute;cessaire...");
			}
		}
		else {
			$("#message").html("Au moins un des champs est vide...");
		}
	}
	
	function validDate(date)
	{	
		if (date.length!=10) return false;
		var match = /^(\d{4})[-](\d{2})[-](\d{2})$/.exec(date);
		if (match==null) return false;
		return true;
	}