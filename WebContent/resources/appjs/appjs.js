/**
 * 
 */
function caculateFn(){
	var amount = document.getElementById("guaranteePrice").value;
	if (!IsNumeric(amount)) {
		//alert("请正确填入数字");
		return;
	}
	 var factAmount = parseFloat(amount);
	 if (!IsNumeric(factAmount)) {
		//alert("请正确填入数字");
	        return false;
	 }
	 if(factAmount<0){
		// alert("请正确填入大于0的数");
	        return false;
	 }
	// alert(factAmount);
	 if(factAmount <= 100){
		 document.getElementById("basicReceiverGainMoney").value = 5;
	 }else if(factAmount <= 200){
		 document.getElementById("basicReceiverGainMoney").value = 8;
	 }else if(factAmount <= 500){
		 document.getElementById("basicReceiverGainMoney").value = 15;
	 }else   {
		 document.getElementById("basicReceiverGainMoney").value = 20;
	 }
}

function IsNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}