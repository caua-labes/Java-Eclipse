/*function alterarURL(codePage) {
  var searchedPage = `zip.com`; 
  if(codePage != undefined){
      searchedPage += `/${codePage}`;
  }
        history.pushState({}, '', `/${searchedPage}`);
    }*/
function searchPage() {
    var codePage = document.getElementById("inputFromCodePage").value
    /*alterarURL(codePage)*/
    getPageIdByCode(codePage)
}

async function getPageIdByCode(codePage) {
    try {
        var linkApiGetPageId = `http://localhost:8080/zipsh/page/${codePage}`;
        const apiRequest = await fetch(linkApiGetPageId, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' }
        })
        const responseApi = await apiRequest.json();
        getPageContentsByPage(responseApi.id)
    }
    catch (err) {
        console.log(err)
    };

}


async function getPageContentsByPage(pageId) {
    var linkApiGetContent = `http://localhost:8080/zipsh/pageContent/pageId/${pageId}`;
    const apiRequest = await fetch(linkApiGetContent, {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' }
    })
    const responseApiListContent = await apiRequest.json();
    clearPage()
    responseApiListContent.forEach(pageContent => {
        showPageContent(pageContent.id, pageContent.content)
    });

}

function clearPage() {
    const navDiv = document.createElement('div');
    navDiv.classList.add('navBar');
    const navTitle = document.createElement('span');
    navTitle.classList.add('navBarTitle');
    navTitle.innerText = 'ZipShare';
    const navInput = document.createElement('input');
    navInput.classList.add('navBarInput');
    navInput.placeholder = 'Your content:';
    const navButton = document.createElement('button');
    navButton.classList.add('navBarButton');
    navButton.innerText = '+';
    navButton.onclick = () => showCreatePage();
    navDiv.appendChild(navTitle);
    navDiv.appendChild(navInput);
    navDiv.appendChild(navButton);
    document.getElementById("navBarBody").appendChild(navDiv);
    document.getElementById("mainDiv").replaceChildren();

}

function showPageContent(id, pageContent) {
    const contentPage = document.createElement('div');
    contentPage.classList.add('contentPage');
    const contentDiv = document.createElement('div');
    contentDiv.classList.add('contentDiv');
    const contentName = document.createElement('span');
    contentName.classList.add('contentName');
    contentName.innerText = pageContent;
    const contentValue = document.createElement('span');
    contentValue.classList.add('contentValue');
    contentValue.innerText = '▽';
    contentValue.onclick = () => downloadButtom(id);
    const contentFunctions = document.createElement('span');
    contentFunctions.classList.add('contentFunctions');
    const deleteButton = document.createElement('button');
    deleteButton.classList.add('deleteButton');
    deleteButton.innerText = '✕';
    deleteButton.onclick = () => delButtom(id);
    const putButton = document.createElement('button');
    putButton.classList.add('editButton');
    putButton.innerText = '△';
    putButton.onclick = () => altButtom(id);
    contentFunctions.appendChild(deleteButton);
    contentFunctions.appendChild(putButton);
    contentDiv.appendChild(contentName);
    contentDiv.appendChild(contentValue);
    contentDiv.appendChild(contentFunctions);
    contentPage.appendChild(contentDiv);
    document.getElementById("mainDiv").appendChild(contentPage);
}


