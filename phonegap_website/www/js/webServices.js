function Qual(x){
    this.challengesFaced = x.challengesFaced;
    this.clientName = x.clientName;
    this.metaDataColourScheme = x.metaDataColourScheme;
    this.metaDataDeloitteServiceLine = x.metaDataDeloitteServiceLine;
    this.metaDataIndustry = x.metaDataIndustry;
    this.metaDataStatus = x.metaDataStatus;
    this.metaDataTag = x.metaDataTag;
    this.problemStatement = x.problemStatement;
    this.projectName = x.projectName;
    this.qualId = x.qualId;
    this.relevanceToClient = x.relevanceToClient;
    this.solution = x.solution;


    this.getInfo = function(){
        return this.challengesFaced + "\n" +
        this.clientName+ "\n" +
        this.metaDataColourScheme + "\n" +
        this.metaDataDeloitteServiceLine + "\n" +
        this.metaDataIndustry + "\n" +
        this.metaDataStatus + "\n" +
        this.metaDataTag + "\n" +
        this.problemStatement + "\n" +
        this.projectName + "\n" +
        this.qualId + "\n" +
        this.relevanceToClient + "\n" +
        this.solution;
    };
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
    $.ajax({
        url: url,
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

var url = "https://onlineportfolio.herokuapp.com/webapi/qual";
function test() {


}
