//Searches for quals, projects, clients or whatever it does

var accountId = -1;
var adminGroupID = -1;
var isClient = false;
var isSuperUser = false;
var sucessfulQuals = 0;

var imported = document.createElement('script');
imported.src = 'js/pnotify.custom.min.js';
document.head.appendChild(imported);

function search(){
    var text = $("#searchBox").val();
    var toShow = false;
    var searchResults = [];

    if (text.length > 0) {
        toShow =  true;

        searchResults = doSearch(text, allQuals, allClients, allProjects);
    }else{
        showResults(false);
        return;
    }

    var resUL = $('#resultUL');
    resUL.empty();


    var len = $('#searchBox').outerWidth() + $('#searchIcon').outerWidth();
    resUL.width(len);

    if(searchResults.length == 0){
        return;
    }

    for(i = 0; i < searchResults.length ; i++) {
        var res = new Results();
        res = searchResults[i];
        //console.log(res.value);

        var li = document.createElement('li');
        var a = document.createElement("a");
        var span = document.createElement("span");

        var h_ref = "#";
        var on_click = '';
        var toggle = '';
        switch (res.type.value){
            case 0: // qual
                h_ref = '#viewQualModal';
                toggle = 'modal';
                on_click = 'viewQual(' + res.id +');showResults(' + false + ');';
                break;
            case 1: // project
                on_click = 'openQualsForProject(' + res.id + ', "' + res.value + '");showResults(' + false + ',"' + '#projects' + '");';
                break;
            case 2: // client
                on_click = 'getProjectforClient(' + res.id + ', "'+ res.value + '");showResults(' + false + ',"' + '#clients' + '");';
                break;
        }

        a.setAttribute("href", h_ref);
        a.setAttribute("onclick", on_click);
        a.setAttribute("data-toggle", toggle);


        span.setAttribute("style", "color: #909090");
        span.appendChild(document.createTextNode(res.type.name + ":"));


        a.appendChild(span);
        a.appendChild(document.createTextNode( '\u00A0' + " "));
        a.appendChild(document.createTextNode(res.value));

        li.appendChild(a);
        resUL.append(li);

    }

    showResults(toShow)

}

function showResults(toShow, tab) {

    $('#resultLI').toggleClass('open', toShow);
    if (toShow == false) {
        $("#searchBox").val("");
    }


    if (tab == "#quals") {
        $('#clients').removeClass('active'); // remove active class from tabs
        $('#projects').removeClass('active'); // remove active class from tabs
        $(tab).addClass('active');

    } else if (tab == "#projects") {
        $('#clientsTab').removeClass('active');
        $('#qualsTab').removeClass('active');
        $('#projectsTab').addClass('active');

        $('#clients').removeClass('active'); // remove active class from tabs
        $('#quals').removeClass('active'); // remove active class from tabs
        $(tab).addClass('active'); // add active class to clicked tab


    } else if (tab == "#clients") {
        $('#clientsTab').addClass('active');
        $('#qualsTab').removeClass('active');
        $('#projectsTab').removeClass('active');

        $('#projects').removeClass('active'); // remove active class from tabs
        $('#quals').removeClass('active'); // remove active class from tabs
        $(tab).addClass('active'); // add active class to clicked tab

    } else {
        error("invalid tab");
    }

    getEverything();


}

function createProjectGroup(projectName) {

    if (projGroupID == -1){

        insertProjectGroup(projectName, function () {
            projGroupID = this; //gets the new project ID

            linkQualsAndProject();
            var clientID;
            if ($('#existingClientSelect').is(':checked') == true) {
                clientID = $("#clientDropdown").val();
                assignAccountToProjectGroup(projGroupID, clientID, function() {})
            } else if ($('#newClientSelect').is(':checked') == true) {
                var accountName = $("#modalClientName").val();
                insertAccount(false, accountName, "password", false, function () {
                    assignAccountToProjectGroup(projGroupID, this, function () {
                    })
                });
            }
            linkQualsAndProject();
            assignProjectToAdminGroup(adminGroupID, projGroupID);
        });
    }else{
        linkQualsAndProject();
    }

}

function linkQualsAndProject(){
    for (i = 0; i < qualsToAdd.length; i++) {
        var tempId = qualsToAdd[i];
        assignQualToProjectGroup(projGroupID, tempId);
    }
    qualsToAdd.splice(0, qualsToAdd.length);
}

function getProjects(account_id){
    accountId = account_id;

    getAccountById(account_id, function() {
        var account = this;

        isClient = !account.isAdmin;
        isSuperUser = account.isSuperUser;

        if(!isClient){
            getAdminGroupsByAccount(accountId, function(){
                adminGroupID = this[0].id;
                getProjectsByAdminGroup(adminGroupID, processProjects);
            })
        }else{
            getProjectsByClient(account_id, processProjects);

        }
    });


}

function processProjects(){
    var projects = this;
    var htmlStr = "<h1 id='heading' class='col-md-10'>Projects</h1>";

    for(i = 0; i< projects.length; i++){

        htmlStr += addPortfolioItem(
            '\"openQualsForProject('+projects[i].id+ ', \'' + projects[i].projectGroupName+'\')\"',
            '\"addProjectQualsToGroup('+projects[i].id+')\"',
            projects[i].id,
            projects[i].projectGroupName,
            '\"confirmArchive(\'PROJECT\'' + ", \'" + projects[i].id + '\')\"',
            "",
            "",
            'project'
        )
    }
    $("#projects").html(htmlStr);
}

function generatePassword() {
    var length = 4,
        charset = "0123456789",
        retVal = "";
    for (var i = 0, n = charset.length; i < length; ++i) {
        retVal += charset.charAt(Math.floor(Math.random() * n));
    }
    return retVal;
}

function verifyAccount2(password) {
    verifyAccount(password, function() {
        var account = this;
        if (account == null) {
            return false;
        } else {
            return true;
        }
    });
}

function createClient(clientName, primaryColour, secondaryColour){
    var password = generatePassword();
    while (verifyAccount2(password) == false) {
        password = generatePassword();
    }

    insertAccount(false, clientName, 'qwerty', false, primaryColour, secondaryColour, password, function(){
        loadTab('#clients');
    });

    return password;
}

function archiveQual(qualId){
    deleteQual(qualId);
};

function archiveProject(projectId){
    deleteProjectGroup(projectId);
};

function archiveClient(clientId){
    deleteAccount(clientId);
};

//function confirmArchive(archiveType, itemID){
//    var title;
//    var message;
//
//    //take the user to the top of the screen
//    $('html, body').animate({ scrollTop: 0 }, 'fast');
//
//    if(archiveType == "CLIENT"){
//// //       document.getElementById("divArchiveAlert").style.display = 'block';
// //       document.getElementById("confirmButton").addEventListener("click", function(){
// //           archiveClient(itemID);
// //       });
// //       document.getElementById("cancelButton").addEventListener("click", function(){
// //           document.getElementById("divArchiveAlert").style.display = 'none';
// //       });
// //       title = "";
// //       message = "";
//        (new PNotify({
//            title: 'Confirmation Needed',
//            text: 'Are you sure?',
//            icon: 'glyphicon glyphicon-question-sign',
//            hide: false,
//            confirm: {
//                confirm: true
//            },
//            buttons: {
//                closer: false,
//                sticker: false
//            },
//            history: {
//                history: false
//            }
//        })).get().on('pnotify.confirm', function() {
//                alert('Ok, cool.');
//            }).on('pnotify.cancel', function() {
//                alert('Oh ok. Chicken, I see.');
//            });
//
//
//    }else if(archiveType == "PROJECT"){
//        document.getElementById("divArchiveAlert").style.display = 'block';
//        document.getElementById("confirmButton").addEventListener("click", function(){
//            archiveProject(itemID);
//        });
//        document.getElementById("cancelButton").addEventListener("click", function(){
//            document.getElementById("divArchiveAlert").style.display = 'none';
//        });
//    }else if(archiveType == "QUAL"){
//        //document.getElementById("divArchiveAlert").style.display = 'block';
//        //document.getElementById("confirmButton").addEventListener("click", function(){
//        //    archiveQual(itemID);
//        //});
//        //document.getElementById("cancelButton").addEventListener("click", function(){
//        //    document.getElementById("divArchiveAlert").style.display = 'none';
//        //});
//        (new PNotify({
//            title: 'Confirmation Needed',
//            text: 'Are you sure?',
//            icon: 'glyphicon glyphicon-question-sign',
//            hide: false,
//            confirm: {
//                confirm: true
//            },
//            buttons: {
//                closer: false,
//                sticker: false
//            },
//            history: {
//                history: false
//            }
//        })).get().on('pnotify.confirm', function() {
//                alert('Ok, cool.');
//            }).on('pnotify.cancel', function() {
//                alert('Oh ok. Chicken, I see.');
//            });
//
//    }else{
//        return; // Default if somehow unknown type
//    }
//}

function addPortfolioItem(viewFunc, addFunc, editFunc, name, archiveFunc, clientImg, projectImg, type){
    // var image = isClients ? "\"img/portfolio/roundicons.png\"" : "\"img/portfolio/startup-framework.png\"";

    var locHeight = 210;
    var locWidth = 295;
    var image = determineItemImage(clientImg, projectImg, type);

    //var newImg = new Image();
    //newImg.src = image;
    //var heightLarger = false;

    //setTimeout(function(){
    //    var height = newImg.height;
    //    var width = newImg.width;
    //    heightLarger = (height >= locHeight);
    //
    //    var w = '100%';
    //    var h = 'auto';
    //    if (heightLarger){
    //        h = '100%';
    //        w = 'auto';
    //    }

    $('#itemPic').css('width', '100%');
    $('#itemPic').css('height', 'auto');
    //}, 100);


    var viewFunction = "";
    if(isNumeric(viewFunc)) {
        viewFunction = ' portfolio-link\' data-target=\"#viewQualModal\" data-toggle=\"modal\" onclick=\"viewQual(' +viewFunc + ')\"';
    }else{
        viewFunction = '\' onclick =' +viewFunc + '\"';
    }

    var editFunction = "";
    var inClientTab = false;
    if(type == 'project'){
        editFunction = 'data-toggle=\"modal\" data-target=\"#createProjModal\" onclick=\"editProject(' + editFunc + ')\"';
    }else if(type == 'qual'){
        editFunction = 'data-toggle=\"modal\" data-target=\"#qualModal\" onclick=\"editQual(' + editFunc + ')\"';
    }else if(type == 'client'){
        inClientTab = true;
        editFunction = 'data-toggle=\"modal\" data-target=\"#createClientModal\" onclick=\"editClient(' + editFunc + ')\"';
    }


    var str = "\
    <div class='col-md-3 col-sm-6 portfolio-item'> \
        <a href='#' class='portfolio-link' data-toggle='modal' > \
        <div class='portfolio-hover'> \
            <div class='portfolio-hover-content viewIcon" + viewFunction + ">\
                <i class='fa fa-search-plus fa-7x'></i>\
            </div>";

    if (!isClient) {
        str += "\
            <div class='portfolio-hover-content editIcon' " + editFunction + " >\
                <i class='fa fa-pencil fa-3x'></i>\
            </div>";
        if (!inClientTab) {
            str += "<div class='portfolio-hover-content addIcon' onclick=" + addFunc + ">\
                <i class='fa fa-plus fa-3x'></i>\
            </div>";
        }

        str +="<div class='portfolio-hover-content archiveIcon' onclick=" + archiveFunc + ">\
                <i class = 'fa fa-trash-o fa-3x'> </i>\
            </div>";
    }
    str +="</div>\
    <div class=\"portfolio-image\" style='background-image: url(\"" + image +"\")'>\
    </div>\
        </a> \
        <div class='portfolio-caption'> \
            <h4>"+name+"</h4> \
            </div>\
        </div>";
//<img id='itemPic' style=\"vertical-align: middle;border:none\" src=\"" + image + "\"class=\"main-thumbnail\">\
    return str;
}

function determineItemImage(clientImage, projectImage, type){

    var image = "";
    if(type == 'qual'){
        if(projectImage == null){
            if(clientImage == null){
                return "img/portfolio/startup-framework.png";
            }else{
                return clientImage;
            }

        }else{
            return projectImage
        }
    }else if(type == 'client'){
        return "imgs/client.png";
    }else if(type == 'project'){
        return "imgs/deloitte.jpg";
    }
}


function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

function addProjectQualsToGroup(projectID){

    getQualsByProject(projectID, function(){

        var quals = this;

        sucessfulQuals = quals.length;

        for(i = 0; i< quals.length; i++) {
            addToCart(quals[i].qualId, quals[i].projectName, true);
        }

        var qualOrQuals = sucessfulQuals === 1 ? " qual has " : " quals have ";


        if(sucessfulQuals > 0) {
            console.log(sucessfulQuals);
            new PNotify({
                title: "Success",
                text: sucessfulQuals + qualOrQuals + "been added to the cart",
                icon: true,
                hide: true,
                type: 'success'
            });
        }
        sucessfulQuals = 0;
    });

}

function openQualsForProject(projectID, projectName) {
    getQualsByProject(projectID, function(){

        var quals = this;

        var htmlStr = "<h1 id='heading' class='col-md-10'>Projects</h1>";
        for(i = 0; i< quals.length; i++){
            htmlStr += addPortfolioItem(
                quals[i].qualId,
                '\"addToCart(' + quals[i].qualId + ", \'" + quals[i].projectName + '\')\"',
                quals[i].qualId,
                quals[i].projectName,
                '\"confirmArchive(\'QUAL\'' + ", \'" + quals[i].qualId + '\')\"',
                quals[i].clientImage,
                quals[i].projectImage,
                'qual'
            );
        }
        htmlStr += "<div class='col-md-12'><button type='submit' class='btn btn-lg pull-right' onclick='getProjects(accountId)'>Back To Projects</button></div><br>";
        $("#projects").html(htmlStr);
        $("#heading").html(projectName);

    });


}

function openQualsForClientProject(projectID, projectName) {
    getQualsByProject(projectID, function(){

        var quals = this;

        var htmlStr = "<h1 id='heading' class='col-md-10'>Projects</h1>";
        for(i = 0; i< quals.length; i++){
            htmlStr += addPortfolioItem(
                quals[i].qualId,
                '\"addToCart(' + quals[i].qualId + ", \'" + quals[i].projectName+ '\')\"',
                quals[i].qualId,
                quals[i].projectName,
                '\"confirmArchive(\'QUAL\'' + ", \'" + quals[i].qualId + '\')\"',
                quals[i].clientImage,
                quals[i].projectImage,
                'qual'
            );
        }
        htmlStr += "<div class='col-md-12'><button type='submit' class='btn btn-lg pull-right' onclick='openPrevClientProj()'>Back To Project</button></div><br>";
        $("#clients").html(htmlStr);
        $("#heading").html(projectName);

    });

}

function openPrevClientProj(){
    $("#clients").html(htmlClientProject);
}

function duplicateQual(qual_id){
    console.log(qual_id);
    sessionStorage.clear();
    sessionStorage.setItem("dup_qual_id", qual_id);
    location.href = 'qual_add.html';
}

function viewQual(qual_id){
    console.log(qual_id);
    sessionStorage.setItem("qual_id", qual_id);

    //$('#btnDuplicate').addEventListener("click", function(event) {
    //    duplicateQual(qual_id);
    //    event.preventDefault();
    //});
    if(!isClient) {
        var dup = '<i id="btnDuplicate" class="fa fa-clipboard fa-4x" onclick=\"duplicateQual(' + qual_id + ')\"></i>';
        $('#viewButtons').prepend(dup);
        //document.getElementById('btnDuplicate').setAttribute('onclick', 'duplicateQual(' + qual_id + ')');
    }
    $('#frameViewQual').attr('src', 'view_qual.html');
}

function getQuals(){
    if (isClient) {
        getQualsByAccount(accountId, processQuals);
    }else{
        getQualsByAdminGroup(adminGroupID, processQuals)
    }
}

function processQuals(){
    var quals = this;
    alert_type = "info";

    var htmlStr ="<h1 id='heading' class='col-md-10'>All Quals</h1>";

    if (!isClient){
        htmlStr += "<div class='row-md-12'><button type='submit' class='btn btn-lg pull-right' onclick=\"window.location.href='qual_add.html'\" >Add New Qual</button></div><br>";
    }

    for (i = 0; i < quals.length; i++) {
        htmlStr += addPortfolioItem(
            quals[i].qualId,
            '\"addToCart(' + quals[i].qualId+ ", \'" + quals[i].projectName + '\')\"',
            quals[i].qualId,
            quals[i].projectName,
            '\"confirmArchive(\'QUAL\'' + ", \'" + quals[i].qualId + '\')\"',
            quals[i].clientImage,
            quals[i].projectImage,
            'qual'
        );
    }
    $("#quals").html(htmlStr)

}

function getClients() {

    HoldOn.open();

    var htmlStr ="<h1 id='heading' class='col-md-10'>Clients</h1>";

    getAllClients(function () {
        var clients = this;
        var list = document.getElementById("client_list");
        for (i = 0; i < clients.length; i++) {
            htmlStr += addPortfolioItem(
                "\"getProjectforClient("+clients[i].accountId+ ', \''   +clients[i].accountName+'\')',
                '\"\"',
                clients[i].accountId,
                clients[i].accountName,
                '\"confirmArchive(\'CLIENT\'' + ", \'" + clients[i].accountId + '\')\"',
                "",
                "",
                'client'
            );
        }

        $("#clients").html(htmlStr)

        HoldOn.close();
    });


}

function getProjectforClient(id, clientName){

    HoldOn.open();

    getProjectsByClient(id,function(){
        var projects = this;
        var htmlStr ="<h1 id='heading' class='col-md-10'>"+clientName+"\'s Projects</h1>";
        for(i = 0; i< projects.length; i++){
            htmlStr += addPortfolioItem(
                '\"openQualsForClientProject(' + projects[i].id+ ", \'" + projects[i].projectGroupName + '\')\"',
                '\"addProjectQualsToGroup(\'' + projects[i].id + '\')\"',
                projects[i].id,
                projects[i].projectGroupName,
                '\"confirmArchive(\'PROJECT\'' + ", \'" + projects[i].id + '\')\"',
                "",
                "",
                'project'
            );
        }
        htmlStr += "<div class='col-md-12'><button type='submit' class='btn btn-lg pull-right' onclick='getClients()'>Back To Clients</button></div><br>";
        htmlClientProject = htmlStr;
        $("#clients").html(htmlStr);

        HoldOn.close();

    });

}

// qID = Qual ID
// m = name of qual, used as html element id
function addToCart(qID, m, isProject){

    var duplicate = false;
    for(var id in qualsToAdd){
        if(qualsToAdd[id] == qID){
            duplicate = true;
            if(isProject){
                sucessfulQuals = sucessfulQuals - 1;
            }
        }

    }
    if(duplicate == false){
        qualsToAdd.push(qID);

        var root = document.getElementById("ProjectCart");
        var div = document.createElement("div");
        var spanDiv = document.createElement("div");
        var textDiv = document.createElement("div");
        div.setAttribute('class', 'qual');
        spanDiv.setAttribute('class', 'col-md-1');
        textDiv.setAttribute('class', 'col-md-10');

        var a = document.createElement("a");
        a.setAttribute("href", "#");
        div.id = "deleteQual";

    var span = document.createElement("span");
    span.setAttribute("class","icon");

        var span = document.createElement("span");
        span.setAttribute("class","icon");

        spanDiv.appendChild(span);
        textDiv.appendChild(document.createTextNode(m));


        a.appendChild(spanDiv);
        a.appendChild(textDiv);
        div.appendChild(a);
        div.onclick = function() {this.parentNode.removeChild(this); qualsToAdd.splice(qualsToAdd.indexOf(this),1);}
        div.setAttribute("id", m); // added line
        root.appendChild(div);



        if(!isProject) {
            var notice = new PNotify({
                title: "Success",
                text: "1 Qual has been added to the cart",
                icon: false,
                hide: true,
                type: 'success'
            });
        }
    }else{
        getQualById(qID, function() {
            var qual = this;
            var notice = new PNotify({
                title: "Oops",
                text: qual.projectName+" has already added to cart",
                icon: false,
                hide: true,
                type: 'error'
            });
        });
    }


    //timeout();
}

function toggleDropdown(id){
    $('#' +id).toggleClass('open');
}

function clearModalPic(){
    $('#client-logo').css('background', 'url("")');
}

function loadTab(tab) {

    HoldOn.open();

    if (tab == "#quals") {
        $('#clients').removeClass('active'); // remove active class from tabs
        $('#projects').removeClass('active'); // remove active class from tabs
        $(tab).addClass('active');
        getQuals();

    } else if (tab == "#projects") {
        $('#clients').removeClass('active'); // remove active class from tabs
        $('#quals').removeClass('active'); // remove active class from tabs
        $(tab).addClass('active'); // add active class to clicked tab
        getProjects(accountId);


    } else if (tab == "#clients") {
        $('#projects').removeClass('active'); // remove active class from tabs
        $('#quals').removeClass('active'); // remove active class from tabs
        $(tab).addClass('active'); // add active class to clicked tab
        getClients();

    } else {
        error("invalid tab");
    }

    getEverything();

    setTimeout(function(){
        HoldOn.close();
    },2000);

}

var allProjects = [], allQuals = [], allClients = [];


//need to get these according the the account
function getEverything(){

    getQualsByAccount(accountId,function(){
        allQuals = this;
    });

    //it's actually by account but named client for some reason :/
    getProjectsByClient(accountId,function(){
        allProjects = this;
    });

    getAccountById(accountId, function(){
        if(this.isAdmin){
            getAllClients(function(){
                allClients = this;
            });
        }
    });

}

function resetProjID(){
    projGroupID = -1;
}

function addQual(){
    sessionStorage.setItem("add_qual", "Y");
    $('#qualModalLabel').text("Add New Qual");
    $('#frameQual').attr('src', 'qual_add.html'); // Loading the iframe
}

function editQual(qual_id){
    sessionStorage.setItem("edit_qual_id", qual_id); // Setting edit qual id
    $('#qualModalLabel').text("Edit Existing Qual"); // Changing Title of modal
    $('#frameQual').attr('src', 'qual_add.html'); // Loading the iframe
}

function editClient(account_id) {
    getAccountById(account_id, function() {
        var account = this;
        $("#client_modal_heading").html("Edit Client");
        $("#clientName").val(account.accountName);
        $("#qual_colour_primary").val(account.primaryColour);
        $("qual_colour_secondary").val(account.secondaryColour);
        var htmlStr = "<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>" +
            "The password for the client is: <strong>"+account.password +"</strong>"
        $("#passwordAlert").html(htmlStr);
        $("#passwordAlert").attr('style', '');
    });
}

function editProject(project_id) {
    getProjectById(project_id, function() {
        var project = this;
        $("#project_modal_title").html("Edit Project");
        $("#projName").val(project.projectGroupName);
        getAccountsByProjectGroup(project_id, function () {
            var account = this;
            $("#clientDropdown").select2("val", "'"+account.accountId+"'");
        });
    });
}
function timeout() {
    setTimeout(function () {
        location.reload();
    }, 2000);
}