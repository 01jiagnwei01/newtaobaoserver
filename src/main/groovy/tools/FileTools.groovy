package tools

class FileTools {
	def lineno = 0;
	def readFile(filePath){
		new File(filePath).eachLine { line ->
			println "Line: ${line}"
		}
		lineno++;
		if(lineno >= 100){
			
		}
	}
	static main(args) {
	
	}
}
