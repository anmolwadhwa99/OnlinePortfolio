
//var url = "http://onlineportfolio.herokuapp.com/webapi";
var url = "http://localhost:8080/onlineportfolio/webapi";
var _qual = "/qual";
var _ac = "/account";
var _ag = "/admin";
var _pg = "/projectGroup";
var _proj = "/project";
var _img = "/image";


function Qual(x){
    this.qualId = x.qualId;
    this.projectImage = x.projectImage;
    this.projectName = x.projectName;
    this.clientImage = x.clientImage;
    this.clientName = x.clientName;
    this.problemStatement = x.problemStatement;
    this.challengesFaced = x.challengesFaced;
    this.solutionStatement = x.solutionStatement;
    this.relevanceToClient = x.relevanceToClient;
    this.outcomeStatement = x.outcomeStatement;
    this.subtitle = x.subtitle;
    this.isAnonymous = x.anonymous;
    this.isActive = x.active;
    this.emailButton = x.emailButton;
    this.websiteButton = x.websiteButton;
    this.industry = x.industry;
    this.tags = x.tags;
    this.status = x.status;
    this.serviceLine = x.serviceLine;
    this.anonymousName = x.anonymousName;


    this.getInfo = function(){
        return this.qualId + "\n" +
        this.projectImage + "\n" +
        this.projectName  + "\n" +
        this.clientImage  + "\n" +
        this.clientName  + "\n" +
        this.problemStatement  + "\n" +
        this.challengesFaced  + "\n" +
        this.solutionStatement  + "\n" +
        this.relevanceToClient  + "\n" +
        this.outcomeStatement  + "\n" +
        this.subtitle + "\n" +
        this.isAnonymous  + "\n" +
        this.isActive + "\n" +
        this.emailButton  + "\n" +
        this.websiteButton  + "\n" +
        this.industry  + "\n" +
        this.tags + "\n" +
        this.status  + "\n" +
        this.serviceLine  + "\n" +
        this.anonymousName;
    };
}

function ProjectGroup(x){
    this.id = x.projGroupId;
    this.projectGroupName = x.projGroupName;
    this.isActive = x.active;
    //this.quals = x.quals;
    //this.accountsProj = x.accountsProj;

    this.getInfo = function(){

        return this.id + "\n" +
            this.projectGroupName + "\n" +
            this.isActive;
        //this.quals + "\n" +
        //this.accountsProj;
    };
}

function Account(x){
    this.accountId = x.accountId;
    this.accountName = x.accountName;
    this.password = x.password;
    this.isAdmin = x.admin;
    this.isSuperUser = x.superUser;
    this.isActive = x.active;
    this.primaryColour = x.primaryColour;
    this.secondaryColour = x.secondaryColour;
    this.accentColour = x.accentColour;

    this.getInfo = function(){
        return this.accountId + "\n" +
            this.accountName + "\n" +
            this.password + "\n" +
            this.isAdmin + "\n" +
            this.isSuperUser;
    }

}

function AdminGroup(x){
    this.id = x.adminGroupId;
    this.name = x.adminGroupName;
    this.isActive = x.active;

    this.getInfo = function(){
        return this.id + "\n" + this.name;
    }
}

function Pic(x){
    this.active = x.active;
    this.imageId = x.imageId;
    this.publicId = x.publicId;
    this.imageType = x.imageType;
    this.imageUrl = x.imageUrl;

    this.getInfo = function(){
        return this.active + "\n" +
            this.imageId + "\n" +
            this.publicId + "\n" +
            this.imageType + "\n" +
            this.imageUrl;
    }
}

// === Search ======================================================

var resType = {
    QUAL : {value: 0, name: "Qual", code: "Q"},
    PROJECT : {value: 1, name: "Project", code: "P"},
    CLIENT : {value: 2, name: "Client", code: "C"}
};
function Results(){
    id = 0;
    type = resType;
    value =  "";
}

function doSearch(str, quals, clients, projects){

    if (str === ''){
        return [];
    }
    str = str.toLowerCase();

    var resultArray = [];

    for (i = 0; i < quals.length; i++){
        var qual = quals[i];
        if (qual.projectName.toLowerCase().indexOf(str) > -1){
            var res = new Results();
            res.id = qual.qualId;
            res.type = resType.QUAL;
            res.value = qual.projectName;

            resultArray.push(res);
        }
    }

    for (i = 0; i < clients.length; i++){
        var client = clients[i];
        try {
            if (client.accountName.toLowerCase().indexOf(str) > -1) {
                var res = new Results();
                res.id = client.accountId;
                res.type = resType.CLIENT;
                res.value = client.accountName;

                resultArray.push(res);
            }
        }catch (e){
            console.log(i);
            console.log(client.getInfo());
        }
    }

    for (i = 0; i < projects.length; i++){
        var proj = projects[i];
        if (proj.projectGroupName.toLowerCase().indexOf(str) > -1){
            var res = new Results();
            res.id = proj.id;
            res.type = resType.PROJECT;
            res.value = proj.projectGroupName;

            resultArray.push(res);
        }
    }
    return resultArray;

}

// === Web Service Connection ======================================

function verifyAccount(pw,callback){
    var methodURL = url + _ac + "/verify/" + pw;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                alert_type = 'error';
                HoldOn.close();
                alert("Incorrect password. Please try again.");
                return null;
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


// === QUALS =======================================================


function getQualById(id, callback){
    var methodURL = url + _qual + "/" + id;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
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
                alert_type = 'error';
                alert("Sorry an error occurred when trying to retrieve all quals.");
                return null;
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

function getAllPublicQuals(callback){
    var methodURL = url + _qual + "/" + "public";
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                alert_type = 'error';
                alert("Sorry an error occurred when trying to retrieve all public quals.");
                return null;
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

function getQualsByAccount(acId, callback){
    var methodURL = url + _qual + _ac + "/" + acId;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
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

function getQualsByProject(projectId, callback){
    var methodURL = url + _qual  + _proj + "/" + projectId;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
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

function getQualsByAdminGroup(agId, callback){
    var methodURL = url + _qual + _ag + "/" + agId;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
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

function getImagesForQual(qId, callback){
    var methodURL = url + _qual + _img + "/" + qId;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var imgs = [];

            var json = JSON.parse(resp);

            for (i = 0; i < json.length; i++ ){
                imgs.push(new Pic(json[i]));
            }

            if(typeof callback == 'function'){

                callback.apply(imgs);
            }

        }
    }
    req.open(method, methodURL, true);
    req.send();
}

function insertQual(isActive, isAnonymous, challengesFaced, clientName, industry
    , tags, outcomeStatement, problemStatement, projectName, relevanceToClient, serviceLine
    , solutionStatement, status, subtitle, email, website, clientImgURL, projectImgURL, callback){
    var methodURL = url + _qual;
    var method = "POST";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                alert_type = 'error';
                alert("Sorry an occurred when trying to add a new qual. Please try again later.");
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);
            var qual = new Qual(json);

            if (typeof callback == 'function'){
                callback.apply(qual.qualId);
            }

        }
    }
    req.open(method, methodURL, true);

    req.setRequestHeader("Content-type","application/json");
    var x = '{'+
        '"isActive": ' + isActive + ','+
        '"isAnonymous": ' + isAnonymous + ','+
        '"challengesFaced": "' + challengesFaced + '",'+
        '"clientImage": "' + clientImgURL + '",'+
        '"clientName": "' + clientName + '",'+
        '"industry": "' + industry + '",'+
        '"tags": "' + tags + '",'+
        '"outcomeStatement": "' + outcomeStatement + '",'+
        '"problemStatement": "' + problemStatement + '",'+
        '"projectName": "' + projectName + '",'+
        '"projectImage": "' + projectImgURL + '",'+
        '"relevanceToClient": "' + relevanceToClient + '",'+
        '"serviceLine": "' + serviceLine + '",'+
        '"solutionStatement": "' + solutionStatement + '",'+
        '"status": "' + status + '",'+
        '"subtitle": "' + subtitle + '",'+
        '"emailButton": "' + email + '",'+
        '"websiteButton":"' + website + '"'+
        '}';

    //console.log(x);
    req.send(x);
}

function deleteQual(id){
    var methodURL = url + _qual + "/" + id;
    var method = "DELETE";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);

            var s = json;

        }
    }
    req.open(method, methodURL, true);
    req.send();

}

function reactivateQual(id){
    var methodURL = url + _qual + "/" + id;
    var method = "PUT";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);

            var s = json;

        }
    }
    req.open(method, methodURL, true);
    req.send();

}

function updateQual(id, isActive, isAnonymous, challengesFaced, clientName, industry
    , tags, outcomeStatement, problemStatement, projectName, relevanceToClient, serviceLine
    , solutionStatement, status, subtitle, email, website, clientLogo, projLogo, callback){
    var methodURL = url + _qual;
    var method = "PUT";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);
            var q = new Qual(json);

            if (typeof callback == 'function'){
                callback.apply(q);
            }


        }
    }
    req.open(method, methodURL, true);
    req.setRequestHeader("Content-type","application/json");
    var x = '{'+
        '"qualId": ' + id + ','+
        '"isActive": ' + isActive + ','+
        '"isAnonymous": ' + isAnonymous + ','+
        '"challengesFaced": "' + challengesFaced + '",'+
        '"clientName": "' + clientName + '",'+
        '"industry": "' + industry + '",'+
        '"tags": "' + tags + '",'+
        '"outcomeStatement": "' + outcomeStatement + '",'+
        '"problemStatement": "' + problemStatement + '",'+
        '"projectName": "' + projectName + '",'+
        '"relevanceToClient": "' + relevanceToClient + '",'+
        '"serviceLine": "' + serviceLine + '",'+
        '"solutionStatement": "' + solutionStatement + '",'+
        '"status": "' + status + '",'+
        '"subtitle": "' + subtitle + '",'+
        '"clientImage": "' + clientLogo + '",'+
        '"projectImage": "' + projLogo + '",'+
        '"emailButton": "' + email + '",'+
        '"websiteButton":"' + website + '"'+
        '}';
    req.send(x);

}

function assignQualToAccount(acId, qId){
    var methodURL = url + _qual + _ac + "/" + acId + "/" + qId;
    var method = "POST";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
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
        '"qualId": ' + qId + ','+
        '"accountId": ' + acId + ''+
        '}';
    console.log(x);
    req.send(x);
}

function assignProjectToAdminGroup(agId, pId){
    var methodURL = url + _pg + _ag + "/" + agId + "/" + pId;
    var method = "POST";

    console.log("Group "+agId+" PROJ "+pId);
    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);
            var qual = new Qual(json);


        }
    }
    req.open(method, methodURL, true);

    req.setRequestHeader("Content-type","application/json");
    //var x = '{'+
    //    '"proId": ' + qId + ','+
    //    '"adminGroupId": ' + agId + ''+
    //    '}';
    console.log();
    req.send();
}

function assignQualToAdminGroup(agId, qId){
    var methodURL = url + _qual + _ag + "/" + agId + "/" + qId;
    var method = "POST";

    console.log(qId);
    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
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
        '"qualId": ' + qId + ','+
        '"adminGroupId": ' + agId + ''+
        '}';
    console.log(x);
    req.send();
}

function assignQualToProjectGroup(pgId, qId){
    var methodURL = url + _qual + _proj + "/" + pgId + "/" + qId;
    var method = "POST";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
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
        '"qualId": ' + qId + ','+
        '"projectGroupId": ' + pgId + ''+
        '}';
    console.log(x);
    req.send(x);
}


// === ACCOUNTS ====================================================


function getAccountById(id, callback){
    var methodURL = url + _ac + "/" + id;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
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
                alert_type = 'error';
                alert("Sorry an error occurred when trying to retrieve all accounts");
                return null;
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
    var methodURL = url + _ac + _ag;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                alert_type = 'error';
                alert("Sorry an occurred when trying to retrieve all admin accounts.");
                return null;
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
    var methodURL = url + _ac + "/client";
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
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

function insertAccount(isAdmin, acName, pw, isSuperUser, primaryColour, secondaryColour, password, callback){
    var methodURL = url + _ac;
    var method = "POST";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);
            var ac = new Account(json);

            if (typeof callback == 'function'){
                callback.apply(ac.accountId);
            }

        }
    }
    req.open(method, methodURL, true);

    req.setRequestHeader("Content-type","application/json");
    var x = '{'+
        '"isAdmin": ' + isAdmin + ','+
        '"accountName": "' + acName + '",'+
        '"password": "' + pw + '",'+
        '"primaryColour": "' + primaryColour + '",'+
        '"secondaryColour": "' + secondaryColour + '",'+
        '"password": "' + password + '",'+
        '"isSuperUser": ' + isSuperUser +
        '}';


    console.log(x);
    req.send(x);
}

function deleteAccount(id){
    var methodURL = url + _ac + "/" + id;
    var method = "DELETE";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);

        }
    }
    req.open(method, methodURL, true);
    req.send();

}

function reactivateAccount(id){
    var methodURL = url + _ac + "/" + id;
    var method = "PUT";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);

        }
    }
    req.open(method, methodURL, true);
    req.send();

}

function assignAccountToAdminGroup(agId, acId){
    var methodURL = url + _ac + _ag + "/" + agId + "/" + acId;
    var method = "POST";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
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
        '"accountId": ' + acId + ','+
        '"adminGroupId": ' + agId + ''+
        '}';
    console.log(x);
    req.send(x);
}

function assignAccountToProjectGroup(pgId, acId){
    var methodURL = url + _ac + _proj + "/" + pgId + "/" + acId;
    var method = "POST";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
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
        '"accountId": ' + acId + ','+
        '"projectGroupId": ' + pgId + ''+
        '}';
    console.log(x);
    req.send(x);
}

function getAccountsByAdminGroup(agId, callback){
    var methodURL = url + _ac + _ag + "/" + agId;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var acs = [];

            var json = JSON.parse(resp);

            for (i = 0; i < json.length; i++ ){
                acs.push(new Account(json[i]));
            }

            if(typeof callback == 'function'){

                callback.apply(acs);
            }

        }
    }
    req.open(method, methodURL, true);
    req.send();
}

function getAccountsByQual(qId, callback){
    var methodURL = url + _ac + _qual + "/" + qId;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var acs = [];

            var json = JSON.parse(resp);

            for (i = 0; i < json.length; i++ ){
                acs.push(new Account(json[i]));
            }

            if(typeof callback == 'function'){

                callback.apply(acs[0]);
            }

        }
    }
    req.open(method, methodURL, true);
    req.send();
}

function getAccountsByProjectGroup(pgId, callback){
    var methodURL = url + _ac + _proj + "/" + pgId;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var acs = [];

            var json = JSON.parse(resp);

            for (i = 0; i < json.length; i++ ){
                acs.push(new Account(json[i]));
            }

            if(typeof callback == 'function'){

                callback.apply(acs);
            }

        }
    }
    req.open(method, methodURL, true);
    req.send();
}


// === ADMIN GROUPS =================================================


function getAdminGroupById(id, callback){
    var methodURL = url + _ag + "/" + id;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
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
                if (req.status == 204){
                    return null;
                }
                alert_type = 'error';
                alert("Sorry an error occurred when trying to retrieve all admin accounts.");
                return null;
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
                return null;
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
    var methodURL = url + _ag + "/" + id;
    var method = "DELETE";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);

        }
    }
    req.open(method, methodURL, true);
    req.send();

}

function reactivateAdminGroup(id){
    var methodURL = url + _ag + "/" + id;
    var method = "PUT";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);

        }
    }
    req.open(method, methodURL, true);
    req.send();

}

function getAdminGroupsByQual(qId, callback){
    var methodURL = url + _ag + _qual + "/" + qId;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);

            var ags = [];

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

function getAdminGroupsByAccount(acId, callback){
    var methodURL = url + _ag + _ac + "/" + acId;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);

            var ags = [];

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


// === PROJECT GROUPS ==============================================

function insertProjectGroup(projGroupName, callback){
    var methodURL = url + _pg;
    var method = "POST";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);
            var pg = new ProjectGroup(json);

            if (typeof callback == 'function'){
                callback.apply(pg.id);
            }


        }
    }
    req.open(method, methodURL, true);

    req.setRequestHeader("Content-type","application/json");
    var x = '{'+
        '"projGroupName": "' + projGroupName + '"'+
        '}';
    console.log(x);
    req.send(x);
}

function getProjectById(id, callback){

    var methodURL = url + _pg +"/" + id;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);
            if(typeof callback == 'function'){

                callback.apply(new ProjectGroup(json));
            }

        }
    }
    req.open(method, methodURL, true);
    req.send();

}

function getProjectsByClient(cId, callback){
    var methodURL = url + _pg + _ac + "/" + cId;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var projects = [];

            var json = JSON.parse(resp);

            for (i = 0; i < json.length; i++ ){
                projects.push(new ProjectGroup(json[i]));
            }

            if(typeof callback == 'function'){

                callback.apply(projects);
            }

        }
    }
    req.open(method, methodURL, true);
    req.send();
}

function getProjectsByAdminGroup(agId, callback){
    var methodURL = url + _pg + _ag + "/" + agId;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var projects = [];

            var json = JSON.parse(resp);

            for (i = 0; i < json.length; i++ ){
                projects.push(new ProjectGroup(json[i]));
            }

            if(typeof callback == 'function'){

                callback.apply(projects);
            }

        }
    }
    req.open(method, methodURL, true);
    req.send();
}

function getAllProjectGroups(callback) {
    var methodURL = url + "/projectGroup";
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                if (req.status == 204) {
                    return null;
                }
                alert_type = 'error';
                alert("Sorry an error occurred when trying to retrieve all projects.");
                return null;
            }

            // Request successful, read the response
            var resp = req.responseText;
            var projects = new Array();

            var json = JSON.parse(resp);

            for (i = 0; i < json.length; i++ ){
                projects.push(new ProjectGroup(json[i]));
            }

            if(typeof callback == 'function'){

                callback.apply(projects);
            }
        }
    }
    req.open(method, methodURL, true);
    req.send();
}

function deleteProjectGroup(id){
    var methodURL = url + _pg + "/" + id;
    var method = "DELETE";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);

        }
    }
    req.open(method, methodURL, true);
    req.send();

}

function reactivateProjectGroup(id){
    var methodURL = url + _pg + "/" + id;
    var method = "PUT";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);

        }
    }
    req.open(method, methodURL, true);
    req.send();

}

// === IMAGES ======================================================
function insertImage(isActive, publicid, imageType, imageUrl , callback){
    var methodURL = url + _img;
    var method = "POST";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);
            var img = new Pic(json);

            if (typeof callback == 'function'){
                callback.apply(img.imageId);
            }


        }
    }
    req.open(method, methodURL, true);

    req.setRequestHeader("Content-type","application/json");
    var x = '{'+
            //"active": false,
            //"imageName": "Trial",
            //"imageType": "CLIENT",
            //"imageUrl": "http://www.google.co.nz"
        '"active": "' + isActive + '",'+
        '"publicId": "' + publicid + '",'+
        '"imageType": "' + imageType + '",'+
        '"imageUrl": "' + imageUrl + '"'+
        '}';
    console.log(x);
    req.send(x);
}

function getImageById(id, callback){

    var methodURL = url + _img +"/" + id;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);
            if(typeof callback == 'function'){

                callback.apply(new Pic(json));
            }

        }
    }
    req.open(method, methodURL, true);
    req.send();

}

function getAllImages(callback) {
    var methodURL = url + _img;
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var imgs = [];

            var json = JSON.parse(resp);

            for (i = 0; i < json.length; i++ ){
                imgs.push(new Pic(json[i]));
            }

            if(typeof callback == 'function'){

                callback.apply(imgs);
            }
        }
    }
    req.open(method, methodURL, true);
    req.send();
}

function getAllClientImages(callback) {
    var methodURL = url + _img + "/logo";
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var imgs = [];

            var json = JSON.parse(resp);

            for (i = 0; i < json.length; i++ ){
                imgs.push(new Pic(json[i]));
            }

            if(typeof callback == 'function'){

                callback.apply(imgs);
            }
        }
    }
    req.open(method, methodURL, true);
    req.send();
}

function getAllProjectImages(callback) {
    var methodURL = url + _img + "/product";
    var method = "GET";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var imgs = [];

            var json = JSON.parse(resp);

            for (i = 0; i < json.length; i++ ){
                imgs.push(new Pic(json[i]));
            }

            if(typeof callback == 'function'){

                callback.apply(imgs);
            }
        }
    }
    req.open(method, methodURL, true);
    req.send();
}

function deleteImage(id){
    var methodURL = url + _img + "/" + id;
    var method = "DELETE";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);

        }
    }
    req.open(method, methodURL, true);
    req.send();

}

function reactivateImage(id){
    var methodURL = url + _img + "/" + id;
    var method = "PUT";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;
            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);

        }
    }
    req.open(method, methodURL, true);
    req.send();

}

function assignImageToQual(iId, qId, callback){
    var methodURL = url + _img + _qual + "/" + iId + "/" + qId;
    var method = "POST";

    var req = createRequest();

    if (req){
        req.onreadystatechange = function(){
            if (req.readyState != 4) return;
            if (req.status != 200) {
                return null;

            }
            // Request successful, read the response
            var resp = req.responseText;
            var json = JSON.parse(resp);
            var img = new Pic(json);

            if (typeof callback == 'function'){
                callback.apply(img.id);
            }

        }
    }
    req.open(method, methodURL, true);

    req.setRequestHeader("Content-type","application/json");
    var x = '{'+
        '"imageId": ' + iId + ','+
        '"qualId": ' + qId + ''+
        '}';
    console.log(x);
    req.send(x);
}
