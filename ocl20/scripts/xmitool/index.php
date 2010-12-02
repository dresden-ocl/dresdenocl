<?php

// You can find a working copy of this script on
// http://fheidenreich.de/xmitool/

echo "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
echo "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n";
echo "<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\" xml:lang=\"en\">\n";
echo "<head>\n";
echo "	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n";
echo "	<title>XMI Transformator</title>\n";
echo "</head>\n";
echo "<body>\n";
echo "<p>\n";
echo "	This tool strips diagram data from XMI files exported by <a href=\"http://gentleware.com/index.php\">Gentleware Poseidon CE 4.0</a> and transforms it to work with the <a href=\"http://dresden-ocl.sf.net\">Dresden OCL 2 Toolkit</a>\n";
echo "</p>\n";
echo "<form enctype=\"multipart/form-data\" action=\"transform.php\" method=\"post\">\n";
echo "<p>\n";
echo "<input type=\"hidden\" name=\"MAX_FILE_SIZE\" value=\"20971520\" />\n";
echo "XMI file: <input name=\"userfile\" type=\"file\" />\n";
echo "<input type=\"submit\" value=\"Transform\" />\n";
echo "</p>\n";
echo "</form>\n";
echo "<p>\n";
echo "	&copy; 2006 <a href=\"mailto:florian.heidenreich@inf.tu-dresden.de\">Florian Heidenreich</a>\n";
echo "</p>\n";
echo "</body>\n";
echo "</html>\n";
?>