Efficient resource management system

University of Groningen

Hein Rietman (s2647931)
Loran Oosterhaven (s2707888)
Johan de Jager (s2780666)

The size of data centers is increasing at a maddening pace. As the size increases, power consumption increases as well and consequently, power consumption becomes a larger and larger expense. To manage power consumption, one needs to gain insights into power consumption of the machines and act upon this. Machines with high power consumption can be replaced or be given lower priority for workload. This is what we aim to assist in with our system.

All machines are connected to a central server. Each machine measures its energy usage, cpu usage, memory usage etc and sends this to the server. The server has three tasks:
store the received information.
visualise the received information in real-time on a dashboard to give an insight into how much energy the machines use, both individually and collectively.
allow the user to shutdown machines from the dashboard if he sees reason to. For example, if a machine is using a lot of energy while being idle it might be a good idea to just shut that machine down.
