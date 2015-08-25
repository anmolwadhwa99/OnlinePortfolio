/**
 * Created by richa on 24/08/15.
 */
function getQuals(){
    getAllQuals(function(){

        var quals = this;
        alert(quals)
        var list = document.getElementById("list");
        var htmlStr = "";
        for(i = 0; i< quals.length; i++){
            var li = document.createElement("li");
            createImageBox()
            li.appendChild(document.createTextNode(quals[i].getInfo()));
            list.appendChild(li);
        }


    })}

//function getClients(){
//    getAllClients(function() {
//        //alert('hi')
//        var clients = this;
//        //alert(clients);
//        var list = document.getElementById("client_list");
//
//        for (i = 0; i < clients.length; i++) {
//            alert(clients[i].accountName);
//            createClientBox(clients[i].accountName);
//        }
//    })
//}

function getProjects(){
    getAllProjectGroups(function(){
        return this;
    })
}

function getQualsForProject(id) {
    alert("right method");
    getQualById(id, function(){
        alert(this.getInfo());
    })
}

function createImageBox(){
    var div = $("#quals").html(
        '<div class="col-md-4 col-sm-6 portfolio-item">' +
        '<a href="#portfolioModal2" class="portfolio-link" data-toggle="modal">' +
        '<div class="portfolio-hover">'+
        '<div class="portfolio-hover-content">' +
        '<i class="fa fa-plus fa-3x"></i>'+
        '</div>' +
        '</div>' +
        '<img src="img/portfolio/startup-framework.png" class="img-responsive" alt="">' +
        '</a>' +
        '<div class="portfolio-caption">' +
        '<h4>Startup Framework</h4>' +
        '<p class="text-muted">Website Design</p>' +
        '</div>' +
        '</div>'
    );
}

function createClientBox(clientName){
    var div = $("#clientsTabPanel").html(
        '<div class="col-md-4 col-sm-6 portfolio-item">'+

    '<div class="portfolio-hover">' +
    '<div class="portfolio-hover-content">' +
    '<a class="portfolio-link"> <i class="fa fa-plus fa-3x"  style="cursor: pointer;" onclick="addToCart('+clientName+')"></i></a>' +
    '</div>' +
    '</div>' +
    '<a href="#portfolioModal1" class="portfolio-link" onclick="getQualsForProject(1)">' +
    '<img src="img/portfolio/roundicons.png" class="img-responsive" alt="">' +
    '</a>' +
    '<div class="portfolio-caption">' +
    '<h4>'+clientName+'</h4>' +
    '<p class="text-muted">Graphic Design</p>' +
    '</div>' +
    '</div>'
    );
}

//        <div class="col-md-4 col-sm-6 portfolio-item">
//        <a href="#portfolioModal2" class="portfolio-link" data-toggle="modal">
//          <div class="portfolio-hover">
//              <div class="portfolio-hover-content">
//                  <i class="fa fa-plus fa-3x"></i>
//              </div>
//          </div>
//        <img src="img/portfolio/startup-framework.png" class="img-responsive" alt="">
//        </a>
//        <div class="portfolio-caption">
//        <h4>Startup Framework</h4>
//        <p class="text-muted">Website Design</p>
//        </div>
//        </div>


