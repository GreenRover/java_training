#!/usr/bin/python
from http.server import BaseHTTPRequestHandler,HTTPServer
import subprocess
import sys

try:
	PORT = int(sys.argv[1])
	CMD = sys.argv[2]
except:
    sys.exit("Usage: python http_server.py PORT CMD")
	
class myHandler(BaseHTTPRequestHandler):
	
	#Handler for the GET requests
	def do_GET(self):
		self.send_response(200)
		self.send_header('Content-type','text/html')
		self.end_headers()
		# Send the html message
		self.wfile.write(self.runDateUtils())
		return
		
	def runDateUtils(self):
		return subprocess.check_output(CMD, shell=True)

try:
	#Create a web server and define the handler to manage the
	#incoming request
	server = HTTPServer(('', PORT), myHandler)
	print('Started httpserver on port ' , PORT)
	
	#Wait forever for incoming htto requests
	sys.stdout.flush()
	server.serve_forever()

except KeyboardInterrupt:
	print('^C received, shutting down the web server')
	server.socket.close()
