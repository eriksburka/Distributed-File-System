# Distributed-File-System
How does the system work?

For a file to be uploaded to the distributed network, it is first split into several chunks of desireable size. Each chunk of bytes is then encrypted using the symmetric encryption algorithm (AES) and published to the blockchain. At the same time the notice of a new offer is sent through a WebSocket service so that receivers can decide if they want to accept the new file which will be stored on their side. To recover the file from the network, the process is reverted.
