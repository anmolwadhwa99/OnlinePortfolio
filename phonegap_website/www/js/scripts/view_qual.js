/**
 * Created by Dheeraj on 20/09/15.
 */

function duplicateQual(qual_id){
    console.log(qual_id);
    sessionStorage.setItem("qual_id", qual_id);
    location.href = 'qual_add.html';
}