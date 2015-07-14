SIP based Voice  platform in pure Java .



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
scalability. [the first diagram here](Include.md). The rest of the
document describes the various stages of the project where each stage
focuses on a small incremental problem to build the complete system in
the end.