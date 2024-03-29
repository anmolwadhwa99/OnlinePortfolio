//
//var url = "https://onlineportfolio.herokuapp.com/webapi";
//var _qual = "/qual";
//var _ac = "/account";
//var _ag = "/admin";
//var _pg = "/projectGroup";
//var _proj = "/project";
//
//function Qual(x){
//    this.qualId = x.qualId;
//    this.projectName = x.projectName;
//    this.clientName = x.clientName;
//    this.problemStatement = x.problemStatement;
//    this.challengesFaced = x.challengesFaced;
//    this.solutionStatement = x.solutionStatement;
//    this.relevanceToClient = x.relevanceToClient;
//    this.outcomeStatement = x.outcomeStatement;
//    this.subtitle = x.subtitle;
//    this.isAnonymous = x.isAnonymous;
//    this.isActive = x.isActive;
//    this.primaryColour = x.primaryColour;
//    this.secondaryColour = x.secondaryColour;
//    this.accentColour = x.accentColour;
//    this.emailButton = x.emailButton;
//    this.websiteButton = x.websiteButton;
//    this.adminGroups = x.adminGroups;
//    this.industry = x.industry;
//    this.tags = x.tags;
//    this.status = x.status;
//    this.serviceLine = x.serviceLine;
//
//    this.getInfo = function(){
//        return this.qualId + "\n" +
//        this.projectName + "\n"  +
//        this.clientName +"\n"  +
//        this.problemStatement + "\n"  +
//        this.challengesFaced + "\n"  +
//        this.solutionStatement + "\n"  +
//        this.relevanceToClient + "\n"  +
//        this.outcomeStatement + "\n"  +
//        this.subtitle + "\n"  +
//        this.isAnonymous + "\n"  +
//        this.isActive + "\n" +
//        this.primaryColour + "\n" +
//        this.secondaryColour + "\n" +
//        this.accentColour + "\n" +
//        this.emailButton + "\n" +
//        this.websiteButton + "\n" +
//        this.adminGroups + "\n" +
//        this.industry + "\n" +
//        this.tags + "\n" +
//        this.status + "\n" +
//        this.serviceLine ;
//    };
//}
//
//function ProjectGroup(x){
//    this.id = x.projGroupId;
//    this.projectGroupName = x.projGroupName;
//    this.quals = x.quals;
//    this.accountsProj = x.accountsProj;
//
//    this.getInfo = function(){
//
//        return this.id + "\n" +
//            this.projectGroupName + "\n" +
//            this.quals + "\n" +
//            this.accountsProj;
//    };
//}
//
//function Account(x){
//    this.accountId = x.accountId;
//    this.accountName = x.accountName;
//    this.password = x.password;
//    this.isAdmin = x.admin;
//    this.isSuperUser = x.superUser;
//
//    this.getInfo = function(){
//        return this.accountId + "\n" +
//                this.accountName + "\n" +
//                this.password + "\n" +
//                this.isAdmin + "\n" +
//                this.isSuperUser;
//    }
//
//}
//
//function AdminGroup(x){
//    this.id = x.adminGroupId;
//    this.name = x.adminGroupName;
//
//    this.getInfo = function(){
//        return this.id + "\n" + this.name;
//    }
//}
//
//function verifyAccount(pw,callback){
//    var methodURL = url + _ac + "/verify/" + pw;
//    var method = "GET";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while sending");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var json = JSON.parse(resp);
//            if(typeof callback == 'function'){
//
//                callback.apply(new Account(json));
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//    req.send();
//}
//
//function createRequest() {
//    var result = null;
//    if (window.XMLHttpRequest) {
//        // FireFox, Safari, etc.
//        result = new XMLHttpRequest();
//        //result.overrideMimeType('application/json'); // Or anything else
//    }
//    else if (window.ActiveXObject) {
//        // MSIE
//        result = new ActiveXObject("Microsoft.XMLHTTP");
//    }
//    else {
//        // No known mechanism -- consider aborting the application
//    }
//    return result;
//}
//
//
//// === QUALS =======================================================
//
//
//function getQualById(id, callback){
//    var methodURL = url + _qual + "/" + id;
//    var method = "GET";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while sending");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var json = JSON.parse(resp);
//            if(typeof callback == 'function'){
//
//                callback.apply(new Qual(json));
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//    req.send();
//
//}
//
//function getAllQuals(callback){
//    var methodURL = url + _qual;
//    var method = "GET";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while sending");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var quals = [];
//
//            var json = JSON.parse(resp);
//
//            for (i = 0; i < json.length; i++ ){
//                quals.push(new Qual(json[i]));
//            }
//
//            if(typeof callback == 'function'){
//
//                callback.apply(quals);
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//    req.send();
//}
//
//function getQualsByAccount(acId, callback){
//    var methodURL = url + _qual + _ac + "/" + acId;
//    var method = "GET";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while sending");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var quals = [];
//
//            var json = JSON.parse(resp);
//
//            for (i = 0; i < json.length; i++ ){
//                quals.push(new Qual(json[i]));
//            }
//
//            if(typeof callback == 'function'){
//
//                callback.apply(quals);
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//    req.send();
//}
//
//function getQualsByProject(projectId, callback){
//    var methodURL = url + _qual  + _proj + "/" + projectId;
//    var method = "GET";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while sending");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var quals = [];
//
//            var json = JSON.parse(resp);
//
//            for (i = 0; i < json.length; i++ ){
//                quals.push(new Qual(json[i]));
//            }
//
//            if(typeof callback == 'function'){
//
//                callback.apply(quals);
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//    req.send();
//}
//
//function getQualsByAdminGroup(agId, callback){
//    var methodURL = url + _qual + _ag + "/" + agId;
//    var method = "GET";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while sending");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var quals = [];
//
//            var json = JSON.parse(resp);
//
//            for (i = 0; i < json.length; i++ ){
//                quals.push(new Qual(json[i]));
//            }
//
//            if(typeof callback == 'function'){
//
//                callback.apply(quals);
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//    req.send();
//}
//
//function insertQual(isActive, isAnonymous, challengesFaced, clientName, industry
//, tags, outcomeStatement, problemStatement, projectName, relevanceToClient, serviceLine, solution
//, solutionStatement, status, subtitle, primaryColour, secondaryColour, accentColour
//, email, website, callback){
//    var methodURL = url + _qual;
//    var method = "POST";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while sending")
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var json = JSON.parse(resp);
//            var qual = new Qual(json);
//
//            if (typeof callback == 'function'){
//                callback.apply(qual.id);
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//
//    req.setRequestHeader("Content-type","application/json");
//    var x = '{'+
//        '"isActive": ' + isActive + ','+
//        '"isAnonymous": ' + isAnonymous + ','+
//        '"challengesFaced": "' + challengesFaced + '",'+
//        '"clientName": "' + clientName + '",'+
//        '"industry": "' + industry + '",'+
//        '"tags": "' + tags + '",'+
//        '"outcomeStatement": "' + outcomeStatement + '",'+
//        '"problemStatement": "' + problemStatement + '",'+
//        '"projectName": "' + projectName + '",'+
//        '"relevanceToClient": "' + relevanceToClient + '",'+
//        '"serviceLine": "' + serviceLine + '",'+
//        '"solution": "' + solution + '",'+
//        '"solutionStatement": "' + solutionStatement + '",'+
//        '"status": "' + status + '",'+
//        '"subtitle": "' + subtitle + '",'+
//        '"primaryColour": "' + primaryColour + '",'+
//        '"secondaryColour": "' + secondaryColour + '",'+
//        '"accentColour": "' + accentColour + '",'+
//        '"emailButton": "' + email + '",'+
//        '"websiteButton":"' + website + '"'+
//    '}';
//
//    //console.log(x);
//    req.send(x);
//}
//
//function deleteQual(id){
//    var methodURL = url + _qual + "/" + id;
//    var method = "DELETE";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while deleting")
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var json = JSON.parse(resp);
//            alert(new Qual(json).getInfo);
//
//            var s = json;
//
//        }
//    }
//    req.open(method, methodURL, true);
//    req.send();
//
//}
//
//function assignQualToAccount(acId, qId){
//    var methodURL = url + _qual + _ac + "/" + acId + "/" + qId;
//    var method = "POST";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while assigning qual to account");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var json = JSON.parse(resp);
//            var qual = new Qual(json);
//
//            if (typeof callback == 'function'){
//                callback.apply(qual.id);
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//
//    req.setRequestHeader("Content-type","application/json");
//    var x = '{'+
//        '"qualId": ' + qId + ','+
//        '"accountId": ' + acId + ''+
//        '}';
//    console.log(x);
//    req.send(x);
//}
//
//function assignQualToAdminGroup(agId, qId){
//    var methodURL = url + _qual + _ag + "/" + agId + "/" + qId;
//    var method = "POST";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while assigning qual to admin group");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var json = JSON.parse(resp);
//            var qual = new Qual(json);
//
//            if (typeof callback == 'function'){
//                callback.apply(qual.id);
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//
//    req.setRequestHeader("Content-type","application/json");
//    var x = '{'+
//        '"qualId": ' + qId + ','+
//        '"adminGroupId": ' + agId + ''+
//        '}';
//    console.log(x);
//    req.send(x);
//}
//
//function assignQualToProjectGroup(pgId, qId){
//    var methodURL = url + _qual + _proj + "/" + pgId + "/" + qId;
//    var method = "POST";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while assigning qual to project group");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var json = JSON.parse(resp);
//            var qual = new Qual(json);
//
//            if (typeof callback == 'function'){
//                callback.apply(qual.id);
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//
//    req.setRequestHeader("Content-type","application/json");
//    var x = '{'+
//        '"qualId": ' + qId + ','+
//        '"projectGroupId": ' + pgId + ''+
//        '}';
//    console.log(x);
//    req.send(x);
//}
//
//
//// === ACCOUNTS ====================================================
//
//
//function getAccountById(id, callback){
//    var methodURL = url + _ac + "/" + id;
//    var method = "GET";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while getting account");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var json = JSON.parse(resp);
//            if(typeof callback == 'function'){
//
//                callback.apply(new Account(json));
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//    req.send();
//
//}
//
//function getAllAccounts(callback){
//    var methodURL = url + _ac;
//    var method = "GET";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while sending");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var accs = [];
//
//            var json = JSON.parse(resp);
//
//            for (i = 0; i < json.length; i++ ){
//                accs.push(new Account(json[i]));
//            }
//
//            if(typeof callback == 'function'){
//
//                callback.apply(accs);
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//    req.send();
//}
//
//function getAllAdmins(callback){
//    var methodURL = url + _ac + _ag;
//    var method = "GET";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while getting all admins");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var accs = [];
//
//            var json = JSON.parse(resp);
//
//            for (i = 0; i < json.length; i++ ){
//                accs.push(new Account(json[i]));
//            }
//
//            if(typeof callback == 'function'){
//
//                callback.apply(accs);
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//    req.send();
//}
//
//function getAllClients(callback){
//    var methodURL = url + _ac + "/client";
//    var method = "GET";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while sending");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var accs = [];
//
//            var json = JSON.parse(resp);
//
//            for (i = 0; i < json.length; i++ ){
//                accs.push(new Account(json[i]));
//            }
//
//            if(typeof callback == 'function'){
//
//                callback.apply(accs);
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//    req.send();
//}
//
//function insertAccount(isAdmin, acName, pw, isSuperUser, callback){
//    var methodURL = url + _ac;
//    var method = "POST";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while creating account");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var json = JSON.parse(resp);
//            var ac = new Account(json);
//
//            if (typeof callback == 'function'){
//                callback.apply(ac.accountId);
//            }
//
//
//        }
//    }
//    req.open(method, methodURL, true);
//
//    req.setRequestHeader("Content-type","application/json");
//    var x = '{'+
//        '"isAdmin": ' + isAdmin + ','+
//        '"accountName": "' + acName + '",'+
//        '"password": "' + pw + '",'+
//        '"isSuperUser": ' + isSuperUser +
//        '}';
//
//
//    console.log(x);
//    req.send(x);
//}
//
//function deleteAccount(id){
//    var methodURL = url + _ac + "/" + id;
//    var method = "DELETE";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while deleting the account");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var json = JSON.parse(resp);
//            alert(new Account(json).getInfo);
//
//        }
//    }
//    req.open(method, methodURL, true);
//    req.send();
//
//}
//
//function assignAccountToAdminGroup(agId, acId){
//    var methodURL = url + _ac + _ag + "/" + agId + "/" + acId;
//    var method = "POST";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while assigning qual to admin group");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var json = JSON.parse(resp);
//            var ac = new Account(json);
//
//            if (typeof callback == 'function'){
//                callback.apply(ac.id);
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//
//    req.setRequestHeader("Content-type","application/json");
//    var x = '{'+
//        '"accountId": ' + acId + ','+
//        '"adminGroupId": ' + agId + ''+
//        '}';
//    console.log(x);
//    req.send(x);
//}
//
//function assignAccountToProjectGroup(pgId, acId){
//    var methodURL = url + _ac + _proj + "/" + pgId + "/" + acId;
//    var method = "POST";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while assigning account to project group");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var json = JSON.parse(resp);
//            var ac = new Account(json);
//
//            if (typeof callback == 'function'){
//                callback.apply(ac.id);
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//
//    req.setRequestHeader("Content-type","application/json");
//    var x = '{'+
//        '"accountId": ' + acId + ','+
//        '"projectGroupId": ' + pgId + ''+
//        '}';
//    console.log(x);
//    req.send(x);
//}
//
//function getAccountsByAdminGroup(agId, callback){
//    var methodURL = url + _ac + _ag + "/" + agId;
//    var method = "GET";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while sending");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var acs = [];
//
//            var json = JSON.parse(resp);
//
//            for (i = 0; i < json.length; i++ ){
//                acs.push(new Account(json[i]));
//            }
//
//            if(typeof callback == 'function'){
//
//                callback.apply(acs);
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//    req.send();
//}
//
//function getAccountsByQual(qId, callback){
//    var methodURL = url + _ac + _qual + "/" + qId;
//    var method = "GET";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while sending");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var acs = [];
//
//            var json = JSON.parse(resp);
//
//            for (i = 0; i < json.length; i++ ){
//                acs.push(new Account(json[i]));
//            }
//
//            if(typeof callback == 'function'){
//
//                callback.apply(acs);
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//    req.send();
//}
//
//function getAccountsByProjectGroup(pgId, callback){
//    var methodURL = url + _ac + _proj + "/" + pgId;
//    var method = "GET";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while sending");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var acs = [];
//
//            var json = JSON.parse(resp);
//
//            for (i = 0; i < json.length; i++ ){
//                acs.push(new Account(json[i]));
//            }
//
//            if(typeof callback == 'function'){
//
//                callback.apply(acs);
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//    req.send();
//}
//
//
//// === ADMIN GROUPS =================================================
//
//
//function getAdminGroupById(id, callback){
//    var methodURL = url + _ag + "/" + id;
//    var method = "GET";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while getting account");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var json = JSON.parse(resp);
//            if(typeof callback == 'function'){
//
//                callback.apply(new AdminGroup(json));
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//    req.send();
//
//}
//
//function getAllAdminGroups(callback){
//    var methodURL = url + _ag;
//    var method = "GET";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while get all groups");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var ags = [];
//
//            var json = JSON.parse(resp);
//
//            for (i = 0; i < json.length; i++ ){
//                ags.push(new AdminGroup(json[i]));
//            }
//
//            if(typeof callback == 'function'){
//
//                callback.apply(ags);
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//    req.send();
//}
//
//function insertAdminGroup(groupName){
//    var methodURL = url + _ag;
//    var method = "POST";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while creating group");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var json = JSON.parse(resp);
//            var ag = new AdminGroup(json);
//
//            if (typeof callback == 'function'){
//                callback.apply(ag.id);
//            }
//
//
//        }
//    }
//    req.open(method, methodURL, true);
//
//    req.setRequestHeader("Content-type","application/json");
//    var x = '{'+
//        '"adminGroupName": "' + groupName + '"'+
//        '}';
//    console.log(x);
//    req.send(x);
//}
//
//function deleteAdminGroup(id){
//    var methodURL = url + _ag + "/" + id;
//    var method = "DELETE";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while deleting the group");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var json = JSON.parse(resp);
//            alert(new Account(json).getInfo);
//
//        }
//    }
//    req.open(method, methodURL, true);
//    req.send();
//
//}
//
//function getAdminGroupsByQual(qId, callback){
//    var methodURL = url + _ag + _qual + "/" + qId;
//    var method = "GET";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while getting account");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var json = JSON.parse(resp);
//
//            var ags = [];
//
//            for (i = 0; i < json.length; i++ ){
//                ags.push(new AdminGroup(json[i]));
//            }
//            if(typeof callback == 'function'){
//
//                callback.apply(ags);
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//    req.send();
//
//}
//
//function getAdminGroupsByAccount(acId, callback){
//    var methodURL = url + _ag + _ac + "/" + acId;
//    var method = "GET";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while getting account");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var json = JSON.parse(resp);
//
//            var ags = [];
//
//            for (i = 0; i < json.length; i++ ){
//                ags.push(new AdminGroup(json[i]));
//            }
//            if(typeof callback == 'function'){
//
//                callback.apply(ags);
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//    req.send();
//
//}
//
//
//// === PROJECT GROUPS ==============================================
//
//function insertProjectGroup(projGroupName, callback){
//    var methodURL = url + _pg;
//    var method = "POST";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while creating group");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var json = JSON.parse(resp);
//            var pg = new ProjectGroup(json);
//
//            if (typeof callback == 'function'){
//                callback.apply(pg.id);
//            }
//
//
//        }
//    }
//    req.open(method, methodURL, true);
//
//    req.setRequestHeader("Content-type","application/json");
//    var x = '{'+
//        '"projGroupName": "' + projGroupName + '"'+
//        '}';
//    console.log(x);
//    req.send(x);
//}
//
//function getProjectById(id, callback){
//
//    var methodURL = url + _pg +"/" + id;
//    var method = "GET";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while sending");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var json = JSON.parse(resp);
//            if(typeof callback == 'function'){
//
//                callback.apply(new ProjectGroup(json));
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//    req.send();
//
//}
//
//function getProjectsByClient(cId, callback){
//    var methodURL = url + _pg + _ac + "/" + cId;
//    var method = "GET";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while sending");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var projects = [];
//
//            var json = JSON.parse(resp);
//
//            for (i = 0; i < json.length; i++ ){
//                projects.push(new ProjectGroup(json[i]));
//            }
//
//            if(typeof callback == 'function'){
//
//                callback.apply(projects);
//            }
//
//        }
//    }
//    req.open(method, methodURL, true);
//    req.send();
//}
//
//function getAllProjectGroups(callback) {
//    var methodURL = url + "/projectGroup";
//    var method = "GET";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while sending");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var projects = new Array();
//
//            var json = JSON.parse(resp);
//
//            for (i = 0; i < json.length; i++ ){
//                projects.push(new ProjectGroup(json[i]));
//            }
//
//            if(typeof callback == 'function'){
//
//                callback.apply(projects);
//            }
//        }
//    }
//    req.open(method, methodURL, true);
//    req.send();
//}
//
//function deleteProjectGroup(id){
//    var methodURL = url + _pg + "/" + id;
//    var method = "DELETE";
//
//    var req = createRequest();
//
//    if (req){
//        req.onreadystatechange = function(){
//            if (req.readyState != 4) return;
//            if (req.status != 200) {
//                alert("An error occurred while deleting the project group");
//                return;
//            }
//            // Request successful, read the response
//            var resp = req.responseText;
//            var json = JSON.parse(resp);
//            alert(new ProjectGroup(json).getInfo);
//
//        }
//    }
//    req.open(method, methodURL, true);
//    req.send();
//
//}