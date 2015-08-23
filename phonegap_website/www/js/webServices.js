
var url = "https://onlineportfolio.herokuapp.com/webapi";
var _qual = "/qual/";
var _ac = "/account/";
var _ag = "/admin/";

function Qual(x){
    this.id = x.qualId;
    this.challengesFaced = x.challengesFaced;
    this.clientName = x.clientName;
    this.metaDataColourScheme = x.metaDataColourScheme;
    this.metaDataDeloitteServiceLine = x.metaDataDeloitteServiceLine;
    this.metaDataIndustry = x.metaDataIndustry;
    this.metaDataStatus = x.metaDataStatus;
    this.metaDataTag = x.metaDataTag;
    this.problemStatement = x.problemStatement;
    this.projectName = x.projectName;
    this.relevanceToClient = x.relevanceToClient;
    this.solution = x.solution;


    this.getInfo = function(){
        return this.id + "\n" +
            this.challengesFaced + "\n" +
            this.clientName+ "\n" +
            this.metaDataColourScheme + "\n" +
            this.metaDataDeloitteServiceLine + "\n" +
            this.metaDataIndustry + "\n" +
            this.metaDataStatus + "\n" +
            this.metaDataTag + "\n" +
            this.problemStatement + "\n" +
            this.projectName + "\n" +
            this.relevanceToClient + "\n" +
            this.solution;
    };
}

function Account(x){
    this.accountId = x.accountId;
    this.admin = x.admin;
    this.pin = x.pin;
    this.userName = x.userName;

    this.getInfo = function(){
        return this.accountId + "\n" +
                this.admin + "\n" +
                this.pin + "\n" +
                this.userName;
    }

}

function AdminGroup(x){
    this.id = x.adminGroupId;
    this.name = x.adminGroupName;

    this.getInfo = function(){
        return this.id + "\n" + this.name;
    }
}

function GetAllAccounts(id, callbackFunction) {
    var opAccount = "http://onlineportfolio.herokuapp.com/webapi/qual";
    console.log("Accessing: " + opAccount);

    var accounts = accessWS(opAccount);
    var list = document.getElementById(id);

    for(i = 0; i< accounts.length; i++){
        list.appendChild(accounts[i]);
    }

}

function accessWS(url){
    var items = [];
    alert("hi");
    $.ajax({
        url: "https://onlineportfolio.herokuapp.com/webapi/qual/59",
        type: 'GET',
        dataType: 'json',
        success: function(xhr){
            var j = xhr[0];
            var q = new Qual(j);
            alert(q.getInfo());
        },
        error: function(){
            alert("fail");
        }
    });

    //.success(function () {alert("Success");})
    //.error(function () {alert("Error Occurred"); items.push("<li id = ErrorCode>Error Occurred</li>");});
    //.complete(function () {alert("Complete");});
    console.log(items);
    return items;
}

function test() {
    var req = createRequest(); // defined above
// Create the callback:
    req.onreadystatechange = function() {
        if (req.readyState != 4) return; // Not there yet
        if (req.status != 200) {
            // Handle request failure here...
            //TODO what if request fails
            return;
        }
        // Request successful, read the response
        var resp = req.responseText;
        var json = JSON.parse(resp);

        var s = json;
        // ... and use it as needed by your app.
    }

    req.open("GET", url+"/qual", true);
    req.send();
}

function createRequest() {
    var result = null;
    if (window.XMLHttpRequest) {
        // FireFox, Safari, etc.
        result = new XMLHttpRequest();
        //result.overrideMimeType('application/json'); // Or anything else
    }
    else if (window.ActiveXObject) {
        // MSIE
        result = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else {
        // No known mechanism -- consider aborting the application
    }
    return result;
}


// === QUALS =========
function getQualById(id, callback){
    var methodURL = url + _qual + id;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                alert("An error occurred while sending");
                return;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);
            if(typeof callback == 'function'){

                callback.apply(new Qual(json));
            }

        }
    }
    req.open(method, methodURL, true);
    req.send();

}

function getAllQuals(callback){
    var methodURL = url + _qual;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                alert("An error occurred while sending");
                return;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var quals = [];

            var json = JSON.parse(resp);

            for (i = 0; i < json.length; i++ ){
                quals.push(new Qual(json[i]));
            }

            if(typeof callback == 'function'){

                callback.apply(quals);
            }

        }
    }
    req.open(method, methodURL, true);
    req.send();
}

function insertQual(clientName, problem, projName, relevance, solution,
challenges, mdIndustry, mdTag, mdStatus, mdServiceLine, mdColourScheme, callback){
    var methodURL = url + _qual;
    var method = "POST";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                alert("An error occurred while sending")
                return;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);
            var qual = new Qual(json);

            if (typeof callback == 'function'){
                callback.apply(qual.id);
            }

        }
    }
    req.open(method, methodURL, true);

    req.setRequestHeader("Content-type","application/json");
    var x = '{'+
        '"clientName": "' + clientName + '",'+
        '"problemStatement": "' + problem + '",'+
        '"projectName": "' + projName + '",'+
        '"relevanceToClient": "' + relevance + '",'+
        '"solution": "' + solution + '",'+
        '"challengesFaced": "' + challenges + '",'+
        '"metaDataIndustry": "' + mdIndustry + '",'+
        '"metaDataTag": "' + mdTag + '",'+
        '"metaDataStatus": "' + mdStatus + '",'+
        '"metaDataDeloitteServiceLine":"' + mdServiceLine + '",'+
        '"metaDataColourScheme":"' + mdColourScheme + '"'+
    '}';
    //console.log(x);
    req.send(x);
}

function deleteQual(id){
    var methodURL = url + _qual + id;
    var method = "DELETE";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                alert("An error occurred while deleting")
                return;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);
            alert(new Qual(json).getInfo);

            var s = json;

        }
    }
    req.open(method, methodURL, true);
    req.send();

}

// === ACCOUNTS ======

function getAccountById(id, callback){
    var methodURL = url + _ac + id;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                alert("An error occurred while getting account");
                return;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);
            if(typeof callback == 'function'){

                callback.apply(new Account(json));
            }

        }
    }
    req.open(method, methodURL, true);
    req.send();

}

function getAllAccounts(callback){
    var methodURL = url + _ac;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                alert("An error occurred while sending");
                return;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var accs = [];

            var json = JSON.parse(resp);

            for (i = 0; i < json.length; i++ ){
                accs.push(new Account(json[i]));
            }

            if(typeof callback == 'function'){

                callback.apply(accs);
            }

        }
    }
    req.open(method, methodURL, true);
    req.send();
}

function getAllAdmins(callback){
    var methodURL = url + _ac + "admin";
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                alert("An error occurred while getting all admins");
                return;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var accs = [];

            var json = JSON.parse(resp);

            for (i = 0; i < json.length; i++ ){
                accs.push(new Account(json[i]));
            }

            if(typeof callback == 'function'){

                callback.apply(accs);
            }

        }
    }
    req.open(method, methodURL, true);
    req.send();
}

function getAllClients(callback){
    var methodURL = url + _ac + "client";
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                alert("An error occurred while sending");
                return;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var accs = [];

            var json = JSON.parse(resp);

            for (i = 0; i < json.length; i++ ){
                accs.push(new Account(json[i]));
            }

            if(typeof callback == 'function'){

                callback.apply(accs);
            }

        }
    }
    req.open(method, methodURL, true);
    req.send();
}

function insertAccount(isAdmin, pin, userName){
    var methodURL = url + _ac;
    var method = "POST";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                alert("An error occurred while creating account");
                return;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);
            var ac = new Account(json);

            if (typeof callback == 'function'){
                callback.apply(ac.id);
            }


        }
    }
    req.open(method, methodURL, true);

    req.setRequestHeader("Content-type","application/json");
    var x = '{'+
        '"admin": ' + isAdmin + ','+
        '"pin": "' + pin + '",'+
        '"userName": "' + userName + '"'+
        '}';
    console.log(x);
    req.send(x);
}

function deleteAccount(id){
    var methodURL = url + _ac + id;
    var method = "DELETE";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                alert("An error occurred while deleting the account");
                return;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);
            alert(new Account(json).getInfo);

        }
    }
    req.open(method, methodURL, true);
    req.send();

}

// === ADMIN GROUP ====

function getAdminGroupById(id, callback){
    var methodURL = url + _ag + id;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                alert("An error occurred while getting account");
                return;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);
            if(typeof callback == 'function'){

                callback.apply(new AdminGroup(json));
            }

        }
    }
    req.open(method, methodURL, true);
    req.send();

}

function getAllAdminGroups(callback){
    var methodURL = url + _ag;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                alert("An error occurred while get all groups");
                return;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var ags = [];

            var json = JSON.parse(resp);

            for (i = 0; i < json.length; i++ ){
                ags.push(new AdminGroup(json[i]));
            }

            if(typeof callback == 'function'){

                callback.apply(ags);
            }

        }
    }
    req.open(method, methodURL, true);
    req.send();
}

function insertAdminGroup(groupName){
    var methodURL = url + _ag;
    var method = "POST";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                alert("An error occurred while creating group");
                return;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);
            var ag = new AdminGroup(json);

            if (typeof callback == 'function'){
                callback.apply(ag.id);
            }


        }
    }
    req.open(method, methodURL, true);

    req.setRequestHeader("Content-type","application/json");
    var x = '{'+
        '"adminGroupName": "' + groupName + '"'+
        '}';
    console.log(x);
    req.send(x);
}

function deleteAdminGroup(id){
    var methodURL = url + _ag + id;
    var method = "DELETE";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                alert("An error occurred while deleting the group");
                return;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);
            alert(new Account(json).getInfo);

        }
    }
    req.open(method, methodURL, true);
    req.send();

}