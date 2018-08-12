# zaleniumforTSSGQA
very bitty Java/Maven/Selenium/Zalenium project for TSSG-QA


TSSG-QA'ers:

Besides your IDE and Java, you will need to have already: Maven, TestNG

Docker commands for running Zalenium

	setup:
		install docker:  https://docs.docker.com/engine/installation/
		once installed: 
			start Docker Quickstart Terminal
				*** for the purposes of this doc, 
					I'm assuming IP is 192.168.99.100, 
					but it is specified in DQT as soon as you start 
					- need this later (see web links)
			pull 2 images:
				docker pull elgalu/selenium
				docker pull dosel/zalenium

	starting: command:
		docker run \
			--rm \
			-ti \
			--name zalenium \
			-p 4444:4444 \    
			-v /var/run/docker.sock:/var/run/docker.sock \    
			-v /tmp/videos:/home/seluser/videos \    
			--privileged dosel/zalenium \
			start --timeZone "America/New_York"

			(optional: --desiredContainers 4  // defaults to 2 anyway and, being Zalenium, expands)

	Zalenium uses Docker, which starts VirtualBox, which you may need to edit the settings of to get things to *really* run.
		use VirtualBox interface (under Oracle), 
		right-click the "machine" (probably "default")
		pick Settings and increase
			motherboard (I used 4 GB)
			processor (I used 1 CPU)
			video memory (I used 64 MB)

	managing: docker commands:
		docker version
		docker info
		docker ps
		docker images   // lists your *images* - not containers
		docker ps -aq   // lists containers
				// I only needed this once, when things were thoroughly screwed up after one particularly disastrous run and none of the containers was responding - generally, if you have containers, you are in the middle of running them and don't have a prompt
		docker rm $(docker kill $(docker ps -aq))  // kill containers
		docker rmi $(docker images -q)  // remove *images* (to download newer ones)

	managing: web links you may find helpful:
		https://docs.docker.com/engine/installation/   // docker
		https://hub.docker.com/r/elgalu/selenium/tags/   // check elgalu/selenium versions, updates
		https://hub.docker.com/r/dosel/zalenium/tags/    // check dosel/zalenium versions, updates
		https://opensource.zalando.com/zalenium/#usage
		http://192.168.99.100:4444/dashboard/  // Zalenium dashboard - YOUR IP MAY DIFFER - from here you can replay videos of your tests
		http://192.168.99.100:4444/grid/admin/live  // Zalenium "Live Preview" - watch your tests run! - click "Interact via VNC" to interact with container
		
For those of you not already familiar with TestNG:

	http://testng.org/doc/index.html
	https://github.com/cbeust/testng/
	IntelliJ issues with TestNG: 
		https://youtrack.jetbrains.com/issues?q=%23%7BUnit%20Testing.%20TestNG%7D%20
	https://stackoverflow.com/search?q=testng

	methods: order of processing:
		@BeforeTest
		@BeforeClass
		@BeforeMethod
		@Test
		@AfterMethod
		@AfterClass
		@AfterTest
	requires testng.xml configuration file(s) - mine is at top of project but should be in src/test/resources, my bad
	heirarchy of XML tags in testng.xml:
		<suite ...>	// in <suite>, you can specify: parallel="true" , and thread-count="2"  (or however many you want)
		<test ...>
		<parameter ...>    // optional, but this is how I set browser to test with
		<classes>
		<class ...>
		<methods>	// you don't need the methods section necessarily, just specifies subset of @Test's to run
		<include ...>	// names of @Test's
		</methods>
		</class>
		</classes>
		</test>
		</suite>
	to run, Run the testng.xml file, do not run from within the class of test cases

Uhhhh do you need to know my versions as of this instant?

	IntelliJ:  2018.2
	Java:  jdk1.8.0_181
	Maven:  only 3.3.9! i'd better update
	TestNG is in the .pom file:  6.14.3
	Selenium is also in the .pom:  3.13.0
	VirtualBox:  5.2.8 r121009 (Qt5.6.2)
	Docker:  18.03.0-ce, build 0520e24302
	elgalu/selenium image:  3.13.0-p3
        dosel zalenium image:  3.13.0b
	sheesh what else? current version of my TV's OS: 1200...?  


Surely that's enough to get you guys up and running (those of you with enough RAM to spare).

