function checkImageType(fileName) {
	var pattern = /jpg|gif|png|jpeg/i;
	return fileName.match(pattern);
}

function getFileInfo(fname) {
	var fileName, imgSrc, getLink;
	var fileLink;
	
	if(checkImageType(fname)) {
		imgSrc = "/displayFile?fileName=" + fname;
		fileLink = fname.substr(14);
		
		var front = fname.substr(0, 12);
		var end = fname.substr(14);
		
		getLink = "/displayFile?fileName=" + front + end;
	} else {
		imgSrc = "/resources/dist/img/file_icon.png";
		fileLink = fname.substr(12);
		getLink = "/displayFile?fileName=" + fname;
	}
	
	fileName = fileLink.substr(fileLink.indexOf("_")+1);
	
	return {fileName:fileName, imgSrc:imgSrc, getLink:getLink, fname:fname};
}