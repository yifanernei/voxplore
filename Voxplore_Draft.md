# Introduction #

The project aims at building a voice platform that supports VoiceXML
based interactive voice response and call control from the session
initiation protocol (SIP) user agents. A call can originate from SIP
phone or from regular telephone via a SIP-PSTN gateway and reaches the
voice platform system. The system retrieves a target IVR application
via web (HTTP). The target application URL may be received in the SIP
message or may be pre-configured in the system. The application
written in a sub-set of VoiceXML tags determines how the system
behaves for that call, e.g., what prompts are played, what DTMF digits
are collected, or where the call is transferred or bridged.

The idea is similar to the [url
http://www.cs.columbia.edu/~kns10/software/sipvxml/sipvxml.html
sipvxml] project except that we use Java for robustness and
scalability. The Basic flow of depicting how application will interact with SIP clients is given here https://docs.google.com/open?id=0B13O1u_jaDvGc2VlM09fT0RSSGc. The rest of the
document describes the various stages of the project where each stage
focuses on a small incremental problem to build the complete system in
the end.

# Modules #

The whole application is divided into 3 major modules. Each module is described as below in corresponding steps.

## Step 1 ##

The first step is to create a simple VoiceXML interpreter engine that
can understand a simple application and perform various functions as
per a sub-set of the tags. First create 4-5 examples of simple vxml
pages. These should include only simple tags like form, block, prompt,
meta, var, filled, catch, help. It should not include any ECMAscript.
Create a list of sub-set of tags that you will support and get it
approved by your mentor. The form interpretation algorithm is the core
of the system and we will focus on that. Write a simple Java program
that reads a vxml file, parses the XML from the file, creates the DOM
tree, and then implements the form interpretation algorithm. The
VoiceXML specification describes in detail how the algorithm works.
The input file name and any options are taken from the command line.
The program prints out the actions it is taking such as if there is a
prompt to be played out it print "PROMPT: the text of the prompt". If
there is any input to be received by the vxml application, it reads
the user input from console. This removes any text-to-speech or DTMF
detection related complexity from the first step. At the end of this
step you have the core of the algorithm implemented as a program that
takes a vxml file, and interprets it, printing out any action, and
taking any user input from console.

## Step 2 ##
## Step 3 ##
# Details #

Add your content here.  Format your content with:
  * Text in **bold** or _italic_
  * Headings, paragraphs, and lists
  * Automatic links to other wiki pages