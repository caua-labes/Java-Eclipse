function getPageContent(codePage){
    try{
        var linkApi = "localhost:8080/zipsh/page/"+codePage;
        fetch(linkApi).then(data => data.json()).then(item => item.forEach(element => {
            document.getElementById("mainDiv").innerHTML += `<div></div>`
        }))
    }
    
}

function clearPage(){
    document.getElementById("mainDiv").innerHTML = "";
}

function showPageContent(id, pageContent, contentName){
    document.getElementById("mainDiv").innerHTML = `
    
    
    `;
}