# Virtualization: Session Revision Notes

## What is Virtualization?
- **Definition:**  
  Virtualization is the process of creating a software-based (virtual) version of something—be it compute, storage, networking, servers, or applications. It enables you to run multiple virtual environments on a single physical machine.
- **Key Component:**  
  **Hypervisor** – the software layer that sits between the physical hardware and the virtual machines (VMs).

## Types of Hypervisors
- **Type 1 (Bare Metal Hypervisors):**
  - Installed directly on the physical server.
  - Provide lower latency and higher performance.
  - **Examples:**  
    - Microsoft Hyper-V  
    - VMware ESXi  
    - Open Source KVM
- **Type 2 (Hosted Hypervisors):**
  - Run on top of a host operating system.
  - Typically have higher latency.
  - **Examples:**  
    - Oracle VirtualBox  
    - VMware Workstation

## What is a Virtual Machine (VM)?
- **Definition:**  
  A VM is a software-based computer that runs an operating system and applications just like a physical computer.
- **Characteristics:**  
  - Completely independent of other VMs.
  - Can run different operating systems (Windows, Linux, Unix) on the same physical hardware.
  - Highly portable – VMs can be moved between hypervisors on different machines almost instantaneously.

## Benefits of Virtualization
- **Cost Savings:**  
  Consolidates multiple virtual environments on a single piece of infrastructure, reducing physical hardware needs, power consumption, and maintenance costs.
- **Agility & Speed:**  
  Quick provisioning and deployment of VMs enable rapid development and testing cycles.
- **Reduced Downtime:**  
  VMs can be migrated quickly to another hypervisor if a physical host fails, ensuring business continuity.

## Recap of Key Concepts
- **Hypervisors** manage and allocate physical server resources to virtual machines.
- **Type 1 hypervisors** (bare metal) are most common in production due to better performance.
- **Type 2 hypervisors** (hosted) are more common for end-user and development scenarios.
- **VMs** behave like physical computers but offer flexibility in deployment, migration, and resource utilization.


---

# Containerization: Session Revision Notes

## What is Containerization?
- **Definition:**  
  Packaging an application and its dependencies into a lightweight, standalone unit called a container.
- **History:**  
  Originated with Linux’s introduction of C-groups (control groups) in 2008, paving the way for technologies like Docker, Cloud Foundry, and Rocket.
- **Core Concept:**  
  Unlike VMs, containers share the host OS kernel, which makes them much smaller, faster, and more portable.

## VMs vs. Containers
- **Virtual Machines (VMs):**
  - Require a full guest OS, binaries, and libraries.
  - Example: A Node.js app in a Linux VM can be over 400 MB due to the guest OS overhead.
  - Scaling VMs means duplicating the entire OS each time.
- **Containers:**
  - Include only the application, its libraries, and runtime—no full OS.
  - Much more lightweight (e.g., a Node.js container might be under 15 MB).
  - Efficient to scale, saving on resources and reducing incompatibility issues between development and production.

## The Containerization Process
1. **Manifest Creation:**  
   - Write a Dockerfile (or a manifest YAML) that describes how to build your container image.
2. **Image Building:**  
   - Use `docker build` to create an image that packages your application with all necessary dependencies.
3. **Container Deployment:**  
   - Run the image as a container using a runtime engine (like Docker Engine).
   - Containers share the host’s resources efficiently, making them ideal for agile, scalable deployments.

## Benefits of Containerization
- **Portability:**  
  Containers run consistently across various environments (dev, test, production).
- **Agility & Scalability:**  
  Quickly spin up or scale down instances without duplicating heavy OS layers.
- **Resource Efficiency:**  
  Shared resources among containers lead to lower overhead and better performance.
- **Simplified CI/CD:**  
  Consistent container environments streamline continuous integration and delivery processes.

## Real-World Example
- **Scenario:**  
  A developer creates a Node.js app on a MacBook.
- **Traditional VM Approach:**  
  Deploying the app in a Linux VM results in bloated images due to a full guest OS.
- **Containerized Approach:**  
  The app is packaged in a container, using only what’s necessary (app code and libraries), leading to:
  - Smaller image sizes.
  - Faster deployment and scaling.
  - Fewer compatibility issues when moving from local development to production.
- **Modularity:**  
  Different parts of an application (e.g., a Python service for additional features) can run in separate containers, allowing independent scaling and updates.

---

# VM vs Docker: Session Revision Notes

## 1. Introduction
- **Focus:**  
  - Compare traditional Virtual Machines (VMs) with Docker (containers).
  - Understand how each technology works and when to use them.
- **Common Ground:**  
  - Both rely on the concept of virtualization—creating an abstraction layer to run multiple environments on one physical machine.

## 2. Virtual Machines (VMs)
- **How VMs Work:**  
  - **Hypervisor:**  
    - Software layer that creates and manages VMs.
    - Allocates physical hardware (CPU, memory, storage) to each VM.
  - **Components of a VM:**  
    - **Virtual Hardware:** Emulated CPU, memory, storage, and network interfaces.
    - **Guest OS:** Each VM runs its own operating system, independent of others.
- **Key Advantages:**  
  - **Isolation:** Each VM is completely separated with its own kernel.
  - **Diverse OS:** Ability to run different operating systems on the same host.
  - **Legacy Applications:** Ideal for apps that require specific OS versions/configurations.
- **Types of Hypervisors:**  
  - **Type 1 (Bare Metal):**  
    - Runs directly on hardware (e.g., VMware ESXi, Microsoft Hyper-V, KVM).
    - Lower latency, higher performance.
  - **Type 2 (Hosted):**  
    - Runs on top of a host OS (e.g., Oracle VirtualBox, VMware Workstation).
    - Higher latency, less common in production.

## 3. Docker (Containers)
- **How Docker Works:**  
  - **Containerization:**  
    - Packages an application with its dependencies into a container.
    - Virtualizes the operating system rather than the hardware.
  - **Key Components:**  
    - **Docker Engine:** Manages container lifecycle, interacts with the host kernel.
    - **cgroups & Namespaces:**  
      - **cgroups:** Allocate resources (CPU, memory) among containers.
      - **Namespaces:** Provide isolated environments for each container.
    - **Docker Images:**  
      - Built using Dockerfiles; contain code, runtime, libraries, and settings.
    - **Containers:**  
      - Running instances of images; lightweight and quick to start/stop.
- **Advantages of Docker:**  
  - **Lightweight:** No need for a full guest OS, resulting in smaller images.
  - **Speed:** Fast startup times and efficient resource use.
  - **Portability:** Consistent environment from development to production.
  - **Scalability:** Ideal for microservices; easy to spin up multiple containers.
- **Process of Containerization:**  
  1. **Manifest:** Write a Dockerfile (or similar) to define the container.
  2. **Image Build:** Use `docker build` to package the app and dependencies.
  3. **Deployment:** Run the container with a runtime engine (e.g., Docker Engine).

## 4. Use Cases & When to Choose Which
- **Virtual Machines:**  
  - When you need to run diverse or legacy operating systems.
  - For workloads that require full isolation with separate kernels.
- **Docker Containers:**  
  - For modern, cloud-native applications, especially microservices.
  - When rapid deployment, scalability, and resource efficiency are key.
  - Ideal for CI/CD pipelines and agile development environments.
- **Hybrid Approach:**  
  - Organizations often use both: VMs for legacy apps and containers for new microservices.

## 5. Key Takeaways
- **VMs vs. Docker:**  
  - VMs emulate entire hardware systems with full OS instances; Docker containers share the host OS and run only the necessary parts.
- **Efficiency:**  
  - Containers are much more lightweight and efficient compared to VMs.
- **Flexibility:**  
  - Both technologies serve different purposes and can be used together in a hybrid environment.
- **Final Thought:**  
  - Choosing between VMs and Docker depends on your workload needs, resource constraints, and desired agility in your deployment process.
