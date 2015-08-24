/**
 * Created by richa on 24/08/15.
 */
function getQuals(){
    getAllQuals(function(){

        var quals = this;

        var list = document.getElementById("list");

        for(i = 0; i< quals.length; i++){
            var li = document.createElement("li");
            createImageBox()
            li.appendChild(document.createTextNode(quals[i].getInfo()));
            list.appendChild(li);
        }


    })}

function getProjects(){
    getAllQuals(function(){

        var quals = this;

        var list = document.getElementById("list");

        for(i = 0; i< quals.length; i++){
            var li = document.createElement("li");
            createImageBox()
            li.appendChild(document.createTextNode(quals[i].getInfo()));
            list.appendChild(li);
        }


    })}

function getQualsForProject(id) {
    alert("right method");
    getQualById(id, function(){
        alert(this.getInfo());
    })
}

function createImageBox(){
    var div = $("#list").html(
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


