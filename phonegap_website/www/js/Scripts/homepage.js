//Searches for quals, projects, clients or whatever it does

var accountId = -1;

function search(){
    var text = $("#searchBox").val();
    var toShow = false;
    var searchResults = [];

    if (text.length > 0) {
        toShow =  true;

        searchResults = doSearch(text, allQuals, allClients, allProjects);
    }

    var len = $('#searchBox').outerWidth() + $('#searchIcon').width();

    var resLI = $('#resultLI');
    var resUL = $('#resultUL');
    resUL.empty();

    for(i = 0; i < searchResults.length ; i++) {
        var res = new Results();
        res = searchResults[i];
        console.log(res.value);

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
                on_click = 'viewQual(' + res.id +');';
                break;
            case 1: // project
                on_click = "openQualsForProject(" + res.id + ", '" + res.value + "')";
                break;
            case 2: // client
                on_click = 'getProjectforClient(' + res.id + ')';
                break;
        }

        //javascript:ShowOld(2367,146986,2);
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

    resLI.toggleClass('open',toShow);

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
                    assignAccountToProjectGroup(projGroupID, this, function() {})
                });
            } else {
                linkQualsAndProject();
            }
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

    getAccountById(account_id, function() {
        var account = this;
        accountId = account_id;
        getProjectsByClient(account_id, function() {
            var projects = this;
            var htmlStr = "<h1 id='heading'>Projects</h1>";

            for(i = 0; i< projects.length; i++){

                htmlStr += addPortfolioItem(
                    '\"openQualsForProject('+projects[i].id+ ', \'' + projects[i].projectGroupName+'\')\"',
                    '\"addProjectQualsToGroup('+projects[i].id+')\"',
                    '\"alert(\'still need to define this one\')\"',
                    projects[i].projectGroupName,
                    '\"confirmArchive(\'PROJECT\'' + ", \'" + projects[i].id + '\')\"',
                    "",
                    "",
                    'project'
                )
            }
            $("#projects").html(htmlStr);
        });
    });
}

function createClient(clientName){
    insertAccount(false, clientName, 'qwerty', false, function(){
        loadTab('#clients');
    });
}

function archiveQual(qualId){
    deleteQual(qualId);
    document.getElementById("divArchiveAlert").style.display = 'none';
};

function archiveProject(projectId){
    deleteProjectGroup(projectId);
    document.getElementById("divArchiveAlert").style.display = 'none';
};

function archiveClient(clientId){
    deleteAccount(clientId);
    document.getElementById("divArchiveAlert").style.display = 'none';
};

function confirmArchive(archiveType, itemID){
    var title;
    var message;

    //take the user to the top of the screen
    $('html, body').animate({ scrollTop: 0 }, 'fast');

    if(archiveType == "CLIENT"){
        document.getElementById("divArchiveAlert").style.display = 'block';
        document.getElementById("confirmButton").addEventListener("click", function(){
            archiveClient(itemID);
        });
        document.getElementById("cancelButton").addEventListener("click", function(){
            document.getElementById("divArchiveAlert").style.display = 'none';
        });
        title = "";
        message = "";
    }else if(archiveType == "PROJECT"){
        document.getElementById("divArchiveAlert").style.display = 'block';
        document.getElementById("confirmButton").addEventListener("click", function(){
            archiveProject(itemID);
        });
        document.getElementById("cancelButton").addEventListener("click", function(){
            document.getElementById("divArchiveAlert").style.display = 'none';
        });
    }else if(archiveType == "QUAL"){
        document.getElementById("divArchiveAlert").style.display = 'block';
        document.getElementById("confirmButton").addEventListener("click", function(){
            archiveQual(itemID);
        });
        document.getElementById("cancelButton").addEventListener("click", function(){
            document.getElementById("divArchiveAlert").style.display = 'none';
        });
    }else{
        return; // Default if somehow unknown type
    }
};

function addPortfolioItem(viewFunc, addFunc, editFunc, name, archiveFunc, clientImg, projectImg, type){
   // var image = isClients ? "\"img/portfolio/roundicons.png\"" : "\"img/portfolio/startup-framework.png\"";

    var image = determineItemImage(clientImg, projectImg, type);
    var viewFunction = "";
    if(isNumeric(viewFunc)) {
        viewFunction = ' portfolio-link\' href=\"#viewQualModal\" data-toggle=\"modal\" onclick=\"viewQual(' +viewFunc + ')\"';
    }else{
        viewFunction = '\' onclick =' +viewFunc + '\"';
    }
    return "<div class='col-md-3 col-sm-6 portfolio-item'> \
              <a href='#' class='portfolio-link' data-toggle='modal' > \
                <div class='portfolio-hover'> \
                    <div class='portfolio-hover-content viewIcon" + viewFunction + ">\
                        <i class='fa fa-search-plus fa-7x'></i>\
                    </div>\
                    <div class='portfolio-hover-content addIcon' onclick=" + addFunc + ">\
                        <i class='fa fa-plus fa-3x'></i>\
                    </div>\
                    <div class='portfolio-hover-content editIcon' onclick=" + editFunc + ">\
                            <i class='fa fa-pencil fa-3x'></i>\
                    </div>\
                    <div class='portfolio-hover-content archiveIcon' onclick=" + archiveFunc + ">\
                            <i class = 'fa fa-trash-o fa-3x'> </i>\
                    </div>\
                </div> \
                <div class=\"portfolio-image\"> \
                    <img style=\"width: 100%;height: auto;vertical-align: middle;border:none\" src=" + image + " class='main-thumbnail'  alt=''> \
                </div>\
            </a> \
            <div class='portfolio-caption'> \
                <h4>"+name+"</h4> \
            </div>\
        </div>"
}



function determineItemImage(clientImage, projectImage, type){

    var image = ""
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
        return "img/portfolio/roundicons.png";
    }else if(type == 'project'){
        return "img/portfolio/startup-framework.png";
    }

}

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

function addProjectQualsToGroup(projectID){

    getQualsByProject(projectID, function(){

        var quals = this;

        for(i = 0; i< quals.length; i++) {
            addToCart(quals[i].qualId, quals[i].projectName);
        }

    });
}

function openQualsForProject(projectID, projectName) {
    getQualsByProject(projectID, function(){

        var quals = this;

        var htmlStr = "<h1 id='heading'>Projects</h1>";
        for(i = 0; i< quals.length; i++){
            htmlStr += addPortfolioItem(
                quals[i].qualId,
                '\"addToCart(' + quals[i].qualId + ", \'" + quals[i].projectName + '\')\"',
                '\"alert(\'still need to define this one\')\"',
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

        var htmlStr = "<h1 id='heading'>Projects</h1>";
        for(i = 0; i< quals.length; i++){
            htmlStr += addPortfolioItem(
                quals[i].qualId,
                '\"addToCart(' + quals[i].qualId + ", \'" + quals[i].projectName+ '\')\"',
                '\"alert(\'still need to define this one\')\"',
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

function viewQual(qual_id){
    console.log(qual_id);
    sessionStorage.setItem("qual_id", qual_id);
    location.href = "#viewQualModal";
    $('#frameViewQual').attr('src', 'view_qual.html');
    getQualById(qual_id, function() {
        $('#client-logo').attr('src', "");
        var qual = this;
        $('#client-logo').attr('src', qual.clientImage);
    });

}

function getQuals(){
    getQualsByAccount(accountId, function(){
        var quals = this;
        alert_type = "info";
        var htmlStr ="<h1 id='heading' class='col-md-10'>All Quals</h1>";
        htmlStr += "<div class='row-md-12'><button type='submit' class='btn btn-lg pull-right' onclick=\"window.location.href='qual_add.html'\" >Add New Qual</button></div><br>";

        for (i = 0; i < quals.length; i++) {
            alert(quals[i].projectImg)
            htmlStr += addPortfolioItem(
                quals[i].qualId,
                '\"addToCart(' + quals[i].qualId+ ", \'" + quals[i].projectName + '\')\"',
                '\"alert(\'still need to define this one\')\"',
                quals[i].projectName,
                '\"confirmArchive(\'QUAL\'' + ", \'" + quals[i].qualId + '\')\"',
                quals[i].clientImage,
                quals[i].projectImage,
                'qual'
            );
        }
        $("#quals").html(htmlStr)

    });
}

function getClients() {
    getAllClients(function () {
        var clients = this;
        var list = document.getElementById("client_list");
        var htmlStr = "";
        for (i = 0; i < clients.length; i++) {
            htmlStr += addPortfolioItem(
                '\"getProjectforClient('+clients[i].accountId+')\"',
                '\"\"',
                '\"alert(\'still need to define this one\')\"',
                clients[i].accountName,
                '\"confirmArchive(\'CLIENT\'' + ", \'" + clients[i].accountId + '\')\"',
                "",
                "",
                'client'
            );
        }

        $("#clients").html(htmlStr)
    });
}

function getProjectforClient(id){
    getProjectsByClient(id,function(){
        var projects = this;
        var htmlStr = "";
        for(i = 0; i< projects.length; i++){
            htmlStr += addPortfolioItem(
                '\"openQualsForClientProject(' + projects[i].id+ ", \'" + projects[i].projectGroupName + '\')\"',
                '\"addProjectQualsToGroup(\'' + projects[i].id + '\')\"',
                '\"alert(\'still need to define this one\')\"',
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


    })

}

// qID = Qual ID
// m = name of qual, used as html element id
function addToCart(qID, m){

    qualsToAdd.push(qID);
    var ul = document.getElementById("ProjectCart");
    var li = document.createElement("li");
    var a = document.createElement("a");
    a.setAttribute("href", "#");

    var span = document.createElement("span");
    span.setAttribute("class","icon");

    a.appendChild(document.createTextNode(m));
    a.appendChild(span);

    li.appendChild(a);
    li.onclick = function() {this.parentNode.removeChild(this); qualsToAdd.splice(qualsToAdd.indexOf(this),1);}
    li.setAttribute("id", m); // added line
    ul.appendChild(li);

    // displaying notification to user that qual has been added to cart

    //take the user to the top of the screen
    $('html, body').animate({ scrollTop: 0 }, 'fast');

    if(qID != null) {
        document.getElementById("divAddToCartAlert").style.display = 'block';
        $("#displayQualName").text(m);
        document.getElementById("addToCartOkButton").addEventListener("click", function () {
            document.getElementById("divAddToCartAlert").style.display = 'none';
        });
    }
}

function loadTab(tab) {

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

}

var allProjects = [], allQuals = [], allClients = [];

function getEverything(){

    getAllQuals(function(){
        allQuals = this;
    });
    getAllProjectGroups(function(){
        allProjects = this;
    });
    getAllClients(function(){
        allClients = this;
    });
}

function resetProjID(){
    projGroupID = -1;
}