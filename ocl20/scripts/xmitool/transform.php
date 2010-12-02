<?php 
$basedir			= ' CONFIGURE ME ';
$uploaddir		= $basedir . 'models/';
$xmifilename	= $uploaddir . $_FILES['userfile']['name'];
$xslfilename	= $basedir . 'poseidon4.xsl';

if (move_uploaded_file($_FILES['userfile']['tmp_name'], $xmifilename)) {

	// Write download header	
	header("Pragma: public");
	header("Expires: 0");
	header("Cache-Control: must-revalidate, post-check=0, pre-check=0");
	header("Cache-Control: private",false);
	header("Content-Type: application/octet-stream");
	
	// Get contents of XMI and XSL
	$xml = file_get_contents($xmifilename);
	$xsl = file_get_contents($xslfilename) ;

	
	// Perform the transformation
	$xh = xslt_create();
	$args = array (
       '/_xml'    =>    $xml,
       '/_xsl'    =>    $xsl
	);
	$transformed = xslt_process($xh, 'arg:/_xml', 'arg:/_xsl', NULL, $args, array()); 

	// GZip result
	$zipfilename = $xmifilename . '.gz';
	$zp = gzopen($zipfilename, "w9");
	gzwrite($zp, $transformed);
	gzclose($zp);
	
	// Send result
	header("Content-Disposition: attachment; filename=\"".basename($zipfilename)."\";");
	header("Content-Transfer-Encoding: binary");
	header("Content-Length: ".@filesize($zipfilename));
	set_time_limit(0);
	@readfile("$zipfilename") or die("File not found.");
} else {
	print "<html>\n";
	print "<body>\n";
	print "Possible file upload attack!  Here's some debugging info:\n";
	print_r($_FILES);
	print "</body>\n";
	print "</html>";
}

?>