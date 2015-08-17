function GetAllAccounts(id, callbackFunction) {
    var opAccount = "http://onlineportfolio.herokuapp.com/webapi/account?jsoncallback=?";
    console.log("Accessing: " + opAccount);

    var accounts = accessWS(opAccount);
    var list = document.getElementById(id);

    $(list).append(accounts.join(""));
}

function accessWS(url){
    var items = [];
    $.getJSON(url, 'json', function(data){
        $.each(data, function(key, val) {
            items.push("<li id='" + key + "'>" + val + "</li>");
        });
    })
        .success(function () {alert("Success");})
        .error(function () {alert("Error Occurred"); items.push("<li id = ErrorCode>Error Occurred</li>");})
        .complete(function () {alert("Complete");});
    console.log(items);
    return items;
}