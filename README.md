# Efficient resource management system

University of Groningen

Hein Rietman (s2647931)
Loran Oosterhaven (s2707888)
Johan de Jager (s2780666)

# Original description

The size of data centers is increasing at a maddening pace. As the size increases, power consumption increases as well and consequently, power consumption becomes a larger and larger expense.
To manage power consumption, one needs to gain insights into power consumption of the machines and act upon this. 
Machines with high power consumption can be replaced or be given lower priority for workload. This is what we aim to assist in with our system.

All machines are connected to a central server. Each machine measures its energy usage, cpu usage, memory usage etc and sends this to the server. 
The server has three tasks:

1. Store the received information.
2. Visualise the received information in real-time on a dashboard to give an insight into how much energy the machines use, both individually and collectively.
3. Allow the user to shutdown machines or processes from the dashboard if he sees reason to. For example, if a machine is using a lot of energy while being idle it might be a good idea to just shut that machine down.

# Basic design

The client and the server will be a Java application. The webserver will be a NodeJS server.
The server Java application passes information using sockets, REST and JSON to the NodeJS webserver.
The Java client and server communication is done using RMI and a message queue.
For the visual aspect of the dashboard we will probably use bootstrap in combination with a few other resources.

The dashboard will consist of a ist of all clients with some standard information about each client.
For each client we have a more detailed page where we show: CPU usage, RAM usage, number of process, number of threads, amount free space, CPU tempurate, power usage, alive time.
OS name, computer name and username.

Per client we also show a process list. Per process we show the name, path, RAM usage, CPU usage and alive time. 
We will also over options for remote killing, suspending and resuming of a process.

For each client there is also an option to signal a shutdown or a reboot remotely.