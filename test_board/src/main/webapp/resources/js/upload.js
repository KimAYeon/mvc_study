function checkImageType(fileName) {
	var pattern = /jpg|gif|png|jpeg/i;
	return fileName.match(pattern);
}

function getFileInfo(fname) {
	var fileName, imgSrc, getLink;
	var fileLink;
	
	if(checkImageType(fname)) {
		imgSrc = "/board/displayFile?fileName=" + fname; // Thumbnail
		fileLink = fname.substr(14); 
		
		var front = fname.substr(0, 12);
		var end = fname.substr(14);
		
		getLink = "/board/displayFile?fileName=" + front + end; // Not Thumbnail 
	} else {
		imgSrc = "/resources/dist/img/file_icon.png";
		fileLink = fname.substr(12);
		getLink = "/board/displayFile?fileName=" + fname; // 전체 경로
	}
	
	fileName = fileLink.substr(fileLink.indexOf("_")+1); // originalName
	console.log(fileName);
	console.log(imgSrc);
	console.log(getLink);
	console.log(fname);
	
	return {fileName:fileName, imgSrc:imgSrc, getLink:getLink, fname:fname};
}