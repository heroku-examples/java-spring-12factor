---
marp: true
theme: gaia
paginate: true
---

<style>
/* General Styles */
section {
  padding: 40px;
  box-sizing: border-box;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-start; /* Default: align content to the top */
  align-items: flex-start; /* Default: align items to the left */
  text-align: left; /* Default: left align text */
  background-color:rgb(57, 1, 67); /* Heroku Prelude - Light Periwinkle/Purple */
  color: #F0F0F0;
}

section > h1:first-child, section > h2:first-child {
  margin-top: 0; /* Remove default top margin from first heading */
  width: 100%; /* Make headings take full width */
  text-align: center; /* Center main slide headings */
  margin-bottom: 30px; /* Space below main heading */
  color: #F0F0F0;
}

/* General heading color (if not overridden by above) */
section h1, section h2, section h3, section h4, section h5, section h6 {
  color: #F0F0F0;
}

section > p, section > ul, section > ol {
  align-self: stretch; /* Allow text blocks to take available width */
  margin-bottom: 20px; /* Space between paragraphs/lists */
  max-width: 90%; /* Limit width of text blocks for readability */
  margin-left: auto; /* Center text blocks if they are narrower */
  margin-right: auto;
  color: #F0F0F0;
}

section ul, section ol {
  padding-left: 40px; /* Indentation for lists */
  color: #F0F0F0; /* Ensure list item text is also white */
}

section li {
    color: #F0F0F0; /* Ensure list item text itself is white */
}

section a {
  color: #C9C3E6; /* Heroku Prelude - Light Periwinkle/Purple for links, good contrast on dark purple */
  text-decoration: underline;
}

section a:hover {
  color: #A9A3D6; /* Slightly desaturated/lighter version for hover */
}

/* General slide styling improvements (can be expanded) */
section {
  display: flex;
  flex-direction: column;
  justify-content: flex-start; /* Default: align content to top */
  align-items: center; /* Default: center content horizontally */
  text-align: center; /* Default: center text */
  position: relative; /* Needed for absolute positioning of children */
  padding-bottom: 100px; /* Add padding to bottom to ensure space for absolutely positioned items if needed */
}

section > h1, section > h2, section > h3, section > ul, section > ol, section > p:not(:has(img)):not(.app-intro-slide .text-content p) {
  flex-shrink: 0; /* Prevent text elements from shrinking too much */
  text-align: left; /* Align text to left for readability */
  width: 90%; /* Give text blocks a good width */
  margin-left: auto; /* Center the text blocks */
  margin-right: auto;
  z-index: 1; /* Ensure text is above absolutely positioned images */
}
section > h1, section > h2, section > h3 {
  text-align: center; /* Headings can be centered */
}


/* Styles for introduction-slide specifically for image handling */
section.introduction-slide {
  justify-content: flex-start; /* Align main content (text) to the top */
}

section.introduction-slide img[src$="slide3_12factor_logo.png"] {
  position: absolute; /* Position relative to the section */
  bottom: 60px;       /* Increased to move up */
  right: 70px;        /* Increased to move left */
  object-fit: contain;
  max-width: 35%;     /* Increased for size */
  max-height: 30vh;    /* Increased for size */
  z-index: 0; /* Ensure image is behind text if they were to overlap */
}

/* Styles for the factor overview slide (Slide #4) */
section.factor-overview-slide ul {
    list-style-type: none; /* Remove default bullets */
    padding: 0;
    display: flex;
    flex-wrap: wrap; /* Allow items to wrap to next line */
    justify-content: center; /* Center items on the line */
    gap: 10px; /* Spacing between items */
}

section.factor-overview-slide li {
    background-color: #DBD4EB; /* Hexagon fill color */
    color: #333; /* Darker text for contrast, assuming accent1 is light */
    padding: 10px 15px;
    border-radius: 8px; /* Rounded corners instead of hexagon for simplicity */
    font-weight: bold;
}

/* Slide 6 Specific Styles (Community/Contributors) */
section.community-slide .contributors-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(180px, 1fr)); /* Responsive grid */
    gap: 20px;
    width: 100%;
    padding: 10px;
    margin-top: 15px;
}

section.community-slide .contributor {
    text-align: center;
}

section.community-slide .contributor img {
    width: 100px; /* Fixed size for consistency */
    height: 100px;
    border-radius: 50%; /* Circular images */
    object-fit: cover; /* Cover the area, might crop */
    margin-bottom: 5px;
    border: 2px solid #ddd; /* Optional border */
}

section.community-slide .contributor-name {
    font-weight: bold;
    font-size: 0.9em;
}

section.community-slide .contributor-affiliation {
    font-size: 0.8em;
}

section.community-slide .you-circle {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 120px;
    height: 120px;
    border-radius: 50%;
    background-color: #5A1BA9; /* Matches a color from KubeCon PPTX */
    color: white;
    font-size: 2em;
    font-weight: bold;
    margin: 10px auto; /* Centering the 'You' circle */
}

section.community-slide .contributor.blank-placeholder .placeholder-circle {
    width: 100px;
    height: 100px;
    border-radius: 50%;
    background-color: #e0e0e0; /* Light grey */
    border: 2px solid #ccc;   /* Slightly darker border */
    display: flex;
    justify-content: center;
    align-items: center;
    color: #aaa; /* For potential initials or icon */
    font-size: 0.8em; /* For potential initials */
    margin-bottom: 5px;
    margin-left: auto; /* Center block elements if parent is text-align: center */
    margin-right: auto;
}

/* Styles for App Intro slide (Slide #8) */
section.app-intro-slide {
  /* This section will primarily rely on the global 'section' styles for its column flow for H1 */
  /* No specific display:flex or flex-direction here, it will inherit from 'section' */
}

section.app-intro-slide .content-row {
  display: flex;
  flex-direction: row; /* Text and image side-by-side */
  justify-content: space-between; /* Adjust as needed, space-around or space-evenly might also work */
  align-items: flex-start; /* Align items to the top of their row content */
  width: 100%; /* Takes full width of the parent section */
  margin-top: 20px; /* Space between title and this content row */
}

section.app-intro-slide .text-content {
  flex: 1; /* Was flex: 1 */
  /* Let's try a specific basis or width, e.g., flex-basis: 50% or width: 50% */
  /* Or let flex-grow handle it but ensure it doesn't push too much */
  padding-right: 20px; /* Space between text and image column */
  text-align: left;
}

section.app-intro-slide .image-content {
  flex: 1; /* Was flex: 1 */
  /* Similar to text-content, consider flex-basis or width if more control is needed */
  text-align: center; /* Center the image within its column */
}

section.app-intro-slide .image-content img {
  max-width: 100%;
  max-height: 100vh; /* Slightly reduced */
  object-fit: contain;
  border: 1px solid #ccc;
}

section.app-intro-slide .text-content p {
  text-align: left;
  width: 100%;
  margin-left: 0;
  margin-right: 0;
}

/* Styles for Design Goals slide (Slide #10) */
section.design-goals-slide ul {
  font-size: 0.9em; /* Reduce font size for the list */
  /* If still needed after font reduction and text shortening: */
  /* column-count: 2; */
  /* column-gap: 20px; */
  /* width: 95%; */
  /* padding-left: 0; */
  /* list-style-position: inside; */
}

section.design-goals-slide ul li {
  /* break-inside: avoid-column; */ /* Use if column-count is enabled */
  padding-bottom: 0.2em; /* Adjust spacing as needed */
}

/* Styles for Section Title slide (Slide #11) */
section.section-title-slide .logo-container {
  display: flex;
  justify-content: space-around; /* Space them out */
  align-items: center;
  background: white;
  width: 80%; /* Control overall width of the logo area */
  margin: 30px; /* Space below titles  */
  padding: 30px 
}

section.section-title-slide .logo-container img,
section.section-title-slide .logo-container svg {
  max-width: 60%; /* Max width for each logo within the container */
  max-height: 50vh; /* Max height for logos */
  object-fit: contain;
  background:white;
}

/***** Factor Bar Styles (shared across all factor slides) *****/
.factor-bar {
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 80px;
  background: #111;
  color: #fff;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 3vw 0 2vw;
  box-sizing: border-box;
  z-index: 2;
}
.factor-bar.spring {
  background: #fff;
  color: #111;
  border-bottom: 2px solid #eee;
}
.factor-bar-title {
  display: flex;
  align-items: center;
  font-size: 1.25em;
  font-weight: 600;
  gap: 1em;
}
.factor-bar-title .factor-number {
  font-size: 1.3em;
  font-weight: bold;
  margin-right: 0.7em;
  letter-spacing: 0.03em;
}
.factor-bar-logo {
  height: 56px;
  max-width: 120px;
  margin-left: 2em;
}
section.factor-slide[class*="factor-"] {
  padding-top: 60px !important;
}

section[class*="factor-"] {
  padding-top: 20px !important;
}

/* Slide-specific helper classes (add if not present or adjust) */

/* Centered content class for slides needing everything centered */
.centered-content section > *,
.centered-content section h1, /* Ensure h1 within centered also behaves */
.centered-content section h2,
.centered-content section h3,
.centered-content section p,
.centered-content section ul,
.centered-content section ol {
  text-align: center !important; /* Force center text elements */
  align-self: center !important; /* Force center block elements */
  width: auto; /* Allow content to define its width for centering */
  max-width: 90%; /* Still prevent overly wide text */
}

/* Override for headings inside centered-content to ensure they are also centered if they have specific width/text-align */
.centered-content section h1,
.centered-content section h2,
.centered-content section h3 {
    width: 100%; /* Headings should still span, but text inside is centered */
    text-align: center !important;
}

.centered-content section img {
  display: block;
  margin-left: auto;
  margin-right: auto;
  margin-top: 1em; /* Add some space above/below centered images */
  margin-bottom: 1em;
}

/* Highlight class for Call to Action slides */
.highlight-cta section {
  background-color: #f0f0f0; /* A light grey, distinct but not jarring */
  /* Optional: Add a subtle top/bottom border for more emphasis */
  /* border-top: 2px solid #d0d0d0; */
  /* border-bottom: 2px solid #d0d0d0; */
}

/* KubeCon-style Call to Action Slide (Slide #32) */
section.kubecon-cta-slide {
  background-color: #000000 !important;
  color: #FFFFFF !important;
  display: flex; /* Ensure flex is explicitly set */
  flex-direction: column; /* Stack elements vertically */
  justify-content: center !important; /* Center content vertically */
  align-items: center !important;   /* Center content horizontally */
  text-align: center !important;
  padding-top: 40px; /* Add some padding at the top if H1 is removed */
  padding-bottom: 40px; /* Add some padding at the bottom */
}

section.kubecon-cta-slide > h1 { /* Title on this slide (if it existed) */
  color: #FFFFFF; /* Ensure title is white */
  text-align: center !important;
  width: 100%;
}

section.kubecon-cta-slide p.github-link a,
section.kubecon-cta-slide p.github-link { /* Targeting the link and its container */
  font-size: 2.0em; /* Slightly smaller if it's the main text now */
  font-weight: bold;
  color: #FFFFFF !important; /* Ensure link text is white */
  text-decoration: none;
  margin-top: 20px; /* Was 20px below title, can be less now or more if needed */
  margin-bottom: 20px; /* Space before the main QR code */
  text-align: center !important;
}

/* Styling for the moved and enlarged QR code */
section.kubecon-cta-slide img.main-qr-code {
  display: block;
  max-width: 300px;  /* New larger size */
  max-height: 300px; /* New larger size */
  width: auto;
  height: auto;
  margin-top: 15px; /* Space after github link */
  margin-bottom: 30px; /* Space before bottom logos */
  margin-left: auto;
  margin-right: auto;
  object-fit: contain;
}

/* Original .qr-code-logo styling might not be needed if .main-qr-code is specific enough */
/* section.kubecon-cta-slide .final-logos img.qr-code-logo { ... } */

/* Styling for the div holding the two bottom logos */
section.kubecon-cta-slide .bottom-logos {
  display: flex;
  justify-content: space-around;
  align-items: center;
  width: 70%; /* Can be adjusted */
}

section.kubecon-cta-slide .bottom-logos img {
  max-height: 60px; /* Slightly smaller than before to give QR code prominence */
  object-fit: contain;
  margin: 0 10px;
}

/* Q&A Slide Specifics (Slide #34) */
section.qa-slide {
  justify-content: center !important; /* Ensure vertical centering of all content */
}

section.qa-slide h1 {
  font-size: 8em !important; /* Make Q&A text very large */
  font-weight: bold;
  line-height: 1;
  margin-bottom: 0.5em; /* Space between Q&A and thank you */
  /* Color and text-align are inherited from kubecon-cta-slide */
}

section.qa-slide .thank-you-contact {
  margin-top: 20px;
  font-size: 0.8em; /* Smaller text for contact info */
  color: #FFFFFF; /* Ensure this text is also white */
  text-align: center;
}

section.qa-slide .thank-you-contact p {
  margin-bottom: 5px; /* Space between thank you and contact lines */
  color: #FFFFFF !important; /* Ensure this text is also white, overriding general p if needed */
  text-align: center !important; /* Ensure centering */
  width: 100%; /* Take full width for centering */
}

/* Make sure H1 on Slide 33 behaves in flex layout */
section.kubecon-cta-slide[has="img.slide33-qr-code"] > h1 {
    flex-grow: 0;
    flex-shrink: 0;
    margin-bottom: 20px; /* Ensure some space below title */
}

/* Adjust QR on slide 33 if it uses kubecon-cta-slide */
section.kubecon-cta-slide img.slide33-qr-code {
  display: block; 
  max-width: 300px; 
  max-height: 300px; 
  width: auto; 
  height: auto; 
  margin-top: 20px; /* Adjusted from 30px, H1 now has margin-bottom */
  margin-left: auto;
  margin-right: auto;
  object-fit: contain; 
  flex-grow: 0; /* Prevent flex item from growing */
  flex-shrink: 0; /* Prevent flex item from shrinking weirdly */
}

/* Title Slide Specific Styling (Slide #1) */
section.title-slide {
    display: flex !important; /* Ensure it is a flex container */
    flex-direction: column !important; /* Stack children vertically */
    height: 100%; /* Ensure it takes full available height (should be inherited) */
    justify-content: center !important; /* Vertical centering of the content block */
    align-items: center !important;   /* Horizontal centering of the content block */
    text-align: center !important;     /* Ensure text within child elements is centered */
    padding: 20px; /* Adjusted padding for title slide if needed, or use global */
}

section.title-slide > * { /* Apply to direct children like H1, P, DIV */
    text-align: center !important;
}

section.title-slide h1 {
    font-size: 2.8em; /* Adjust as needed */
    margin-bottom: 0.5em;
}

section.title-slide p.subtitle {
    font-size: 1.4em;
    margin-bottom: 1em;
}

section.title-slide div.logos-container {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 30px;
    margin-top: 30px; /* Space above logos if they are part of the centered block */
    width: 100%;
}

section.title-slide div.logos-container img {
    max-height: 70px;
    object-fit: contain;
}

/* Styling for Fenced Code Blocks */
section pre {
  background-color:rgb(39, 1, 44); /* << EXPLORE: Dark grey background for code blocks */
  color: #E0E0E0;            /* << EXPLORE: Light grey text color for code */
  padding: 0.5em;
  border-radius: 4px;
  overflow-x: auto; /* Allow horizontal scrolling for long lines */
  width: 90%; /* Match paragraph width, can be adjusted */
  max-width: 90%;
  box-sizing: border-box;
}

section pre code {
  font-family: 'Courier New', Courier, monospace; /* Monospace font */
  background-color: transparent !important; /* Ensure inner code tag doesn't override pre background */
  color: inherit !important; /* Inherit text color from pre */
  padding: 0; /* Reset padding for inner code if any */
  white-space: pre-wrap; /* Allow wrapping, but respect pre formatting */
  word-wrap: break-word; /* Break long words if necessary */
}

/* Styling for Inline Code Literals */
section code:not(pre code) { /* Target <code> not inside <pre> */
  background-color: #4A0153; /* << EXPLORE: Darker shade of the slide purple, or another dark grey */
  color: #DADADA;            /* << EXPLORE: Light text color for inline code */
  padding: 0.2em 0.4em;
  border-radius: 3px;
  font-family: 'Courier New', Courier, monospace;
  font-size: 0.85em; /* Slightly smaller */
}

</style> 

<!-- _class: title-slide -->

# 12-Factor Apps with Spring Boot
### Simplicity, Scalability, and Best Practices

#### Andrew Fawcett
###### VP Developer Relations, Heroku

<!--
Welcome everyone! I'm Andrew Fawcett, VP of Developer Relations at Heroku.
Today, we're diving into "Building 12-Factor Spring Boot Applications," focusing on how these principles lead to simpler, more scalable, and robust software.
Over the next 50 minutes, we'll explore what the Twelve-Factor App methodology is, why it's crucial for modern cloud-native applications, and how Spring Boot beautifully aligns with these factors.
My goal is for you to leave with practical insights on applying these best practices to your own Spring Boot projects. Let's get started!
-->

---

<!-- _class: introduction-slide -->
# The Modern App Challenge

*   Complexity in development & deployment
*   Scaling applications effectively
*   Ensuring deployment consistency
*   Maintaining applications in cloud environments

<!--
So, why are we here talking about Twelve-Factor Apps?
Well, if you're building applications today, especially for the cloud, you've likely faced some of these common hurdles:
The sheer **complexity** of modern development and deployment cycles can be overwhelming.
**Scaling** our applications up and down to meet demand, without breaking the bank or causing downtime, is a constant concern.
Ensuring that what works in development also works reliably in staging and production – that **deployment consistency** – can be elusive.
And finally, simply **maintaining** these applications effectively in dynamic cloud environments, day in and day out, presents its own set of challenges.
These are the very issues that the Twelve-Factor App methodology aims to address, providing a common vocabulary and a set of best practices to guide us.
-->

---

<!-- _class: introduction-slide -->
# What is the Twelve-Factor App?

*   **Origin:** Heroku more than a decade ago
*   **Purpose:** A manifesto for robust cloud applications.
*   **Core Idea:** Best practices for cloud-native applications.
    *   Designed for automation
    *   Enable portability
    *   Continuous deployment
    *   Scalability
    ![12factor.net Logo](images/slide3_12factor_logo.png)

<!--
More than a decade ago, Heroku developers formulated the Twelve-Factor App methodology. It's essentially a manifesto, a set of best practices for building Software-as-a-Service applications that are robust, maintainable, and scalable in modern cloud environments.

The core ideas revolve around:
- **Automation:** Minimizing setup time and cost for new developers joining a project.
- **Portability:** Ensuring applications can move seamlessly between different execution environments.
- **Cloud-Native Deployment:** Designing apps suitable for deployment on today's cloud platforms.
- **Continuous Deployment & Scalability:** Reducing the gap between development and production, enabling agile continuous deployment, and allowing applications to scale without massive architectural overhauls.

Ultimately, it provides a shared vocabulary and actionable recommendations to build better software that stands the test of time and growth.
-->

---

<!-- _class: factor-overview-slide -->
# The Twelve Factors

<ul>
  <li>CODEBASE</li>
  <li>DEPENDENCIES</li>
  <li>CONFIG</li>
  <li>BACKING SERVICES</li>
  <li>BUILD, RELEASE, RUN</li>
  <li>PROCESSES</li>
  <li>PORT BINDING</li>
  <li>CONCURRENCY</li>
  <li>DISPOSABILITY</li>
  <li>DEV & PROD PARITY</li>
  <li>LOGS</li>
  <li>ADMIN PROCESSES</li>
</ul>

# 12factor.net

<!--
The Twelve-Factor App methodology provides a set of best practices for building applications that are scalable, maintainable, and robust in modern cloud environments. Let's quickly go through what these twelve factors are using the 12factor.net website. We'll be diving deeper into several of these key principles throughout the session to see how they apply directly to Spring Boot development.
-->

---

<!-- _class: community-slide -->
# Active Maintainers

<div class="contributors-grid">
    <div class="contributor">
        <img src="images/slide6_vish_abrams.png" alt="Vish Abrams">
        <div class="contributor-name">Vish Abrams</div>
        <div class="contributor-affiliation">Heroku</div>
    </div>
    <div class="contributor">
        <img src="images/slide6_brian_hammons.png" alt="Brian Hammons">
        <div class="contributor-name">Brian Hammons</div>
        <div class="contributor-affiliation">AWS</div>
    </div>
    <div class="contributor">
        <img src="images/slide6_terence_lee.png" alt="Terence Lee">
        <div class="contributor-name">Terence Lee</div>
        <div class="contributor-affiliation">Heroku</div>
    </div>
    <div class="contributor">
        <img src="images/slide6_james_ward.png" alt="James Ward">
        <div class="contributor-name">James Ward</div>
        <div class="contributor-affiliation">AWS</div>
    </div>
    <div class="contributor">
        <img src="images/slide6_yehuda_katz.png" alt="Yehuda Katz">
        <div class="contributor-name">Yehuda Katz</div>
        <div class="contributor-affiliation">Heroku</div>
    </div>
    <div class="contributor">
        <img src="images/slide6_grace_jansen.png" alt="Grace Jansen">
        <div class="contributor-name">Grace Jansen</div>
        <div class="contributor-affiliation">IBM</div>
    </div>
    <div class="contributor">
        <img src="images/slide6_brett_weaver.png" alt="Brett Weaver">
        <div class="contributor-name">Brett Weaver</div>
        <div class="contributor-affiliation">Intuit</div>
    </div>
    <div class="contributor">
        <img src="images/slide6_evan_anderson.png" alt="Evan Anderson">
        <div class="contributor-name">Evan Anderson</div>
        <div class="contributor-affiliation">Independent</div>
    </div>
    <div class="contributor">
        <img src="images/slide6_joe_kutner.png" alt="Joe Kutner">
        <div class="contributor-name">Joe Kutner</div>
        <div class="contributor-affiliation">Salesforce</div>
    </div>
    <div class="you-circle">?</div>
</div>

<!--
The Twelve-Factor App methodology, while having its roots at Heroku, has benefited from the insights and contributions of many individuals across the industry.
This slide acknowledges some of the key people who were instrumental in its early formulation, evolution, and popularization. Their work laid the foundation for these best practices that help us build more robust and scalable cloud-native applications. And, of course, the community continues to evolve these ideas, and everyone using and adapting these principles is part of that ongoing story.
--> 

---

<!-- _class: introduction-slide -->
# Spring Boot & Twelve-Factor?

*   **Spring Boot Philosophy:**
    *   Convention over configuration
    *   Embedded servers (Tomcat, Jetty, Undertow)
    *   Production-ready features (Actuator)
*   **Natural Alignment:** Spring Boot inherently supports many Twelve-Factor principles.
    *   *We'll explore how!*

<!--
So, we've talked about the challenges and introduced the Twelve-Factor App. Now, why bring Spring Boot into this discussion specifically?

Spring Boot has a philosophy that resonates strongly with cloud-native development:
- It champions **convention over configuration**, meaning you can get started quickly with sensible defaults, reducing boilerplate.
- It comes with **embedded servers** like Tomcat, Jetty, or Undertow, making your applications self-contained and easy to run anywhere.
- It offers **production-ready features** out-of-the-box, such as health checks and metrics via Spring Boot Actuator.

What you'll see is that this philosophy creates a **natural alignment** with many of the Twelve-Factor principles. Spring Boot often makes it easier to adhere to these best practices without a lot of extra effort.
And that's exactly what we're going to explore in more detail – *how* Spring Boot helps us build robust, scalable, and maintainable 12-factor applications.
-->

---

<!-- _class: agenda-slide -->
# Whats Coming Up

1.  **Demo App:** The Twelve-Factor Notepad App
2.  **Explore:** Core Principles with Spring Boot
3.  **Resources:** Sample App, 12factor.net 

<!--
Now that we've set the stage on *why* the Twelve-Factor App methodology is so important for modern cloud-native applications, let's take a quick look at whats instore for the rest of today's session. I'll introduce you to the sample application we'll be using to illustrate these principles, and we'll do a quick tour of its features. After that, we'll dive into 'The "How": Core Principles with Spring Boot.' This is where we'll explore several key factors in detail, looking at how Spring Boot helps us implement them effectively, complete with code examples from our notepad app. Finally, we'll wrap up with some additional resources and Q&A.
--> 

---

<!-- _class: app-intro-slide -->
# The Twelve-Factor Notepad

<div class="content-row">
  <div class="text-content">
  <p>A simple, real-time collaborative notepad application built with Spring Boot, designed to demonstrate Twelve-Factor principles in action.</p>
  <p>Time for Demo #1</p>
  </div>
  <div class="image-content">

  ![Twelve-Factor Notepad App Screenshot](images/slide8_notepad_screenshot.png)

  </div>
</div>

<!--
Alright, now that we have our roadmap, let's meet the application that will be our guide through this exploration of Twelve-Factor principles: The Twelve-Factor Notepad. This is a straightforward, web-based notepad application. What makes it interesting for our session is that it's built using Spring Boot and has been specifically designed to embody the Twelve-Factor methodology. It allows users to create, edit, and delete notes, and a key feature is its real-time collaborative aspect – changes made by one user are instantly visible to others. We'll see how this plays into factors like statelessness and backing services later on. Throughout the next section, we'll be referring back to this app, looking at its architecture and code to see concrete examples of these principles in practice."
-->

---

<!-- _class: new-slide-placeholder -->
# Key Features

*   **Create & Manage Notes:** Easily add new notes with colors.
*   **Edit On-the-Fly:** Modify note content directly on the board.
*   **Organize Visually:** Drag and drop notes to arrange them.
*   **Delete When Done:** Remove notes that are no longer needed.
*   **Real-Time Collaboration:** See changes from other users instantly, making it a shared workspace.

<!--
So, what can this Twelve-Factor Notepad app actually do? Let's quickly run through its core features."
"Users can, of course, **Create & Manage Notes** – adding new sticky notes with titles, text content, and they can assign different colors to them for organization. You can Edit On-the-Fly; just click into a note and start typing.

A key part of the user experience is being able to **Organize Visually** – you can drag and drop notes anywhere on the board to arrange them just how you like. Naturally, you can Delete when done to keep your workspace tidy. And crucially, it supports real-time collaboration. If multiple users are looking at the same notepad, changes made by one person—like adding a note, editing text, or moving a note—are reflected instantly for everyone else. This creates a dynamic, shared environment. These features, while simple, provide a good foundation to explore how the Twelve-Factor principles apply to a real-world application.
-->

---

<!-- _class: design-goals-slide -->
# Implementation Goals

*   **Simplicity & Clarity:** Easy-to-understand codebase and architecture.
*   **Configuration Flexibility:** Different environments without code.
*   **Reliable Data Handling:** Consistent note storage and retrieval.
*   **Responsive Real-time Experience:** Smooth, immediate updates.
*   **Scalability in Mind:** Architected for growth in users and data.


<!--
Before we explicitly map our sample app to the Twelve-Factor methodology, I wanted to touch upon some core implementation goals we had when building it. These are principles that guide well-architected applications:
First, Simplicity & Clarity – we aimed for a codebase and architecture that's straightforward and easy for developers to understand and work with. Configuration Flexibility was key – the app needed to adapt easily to different environments without needing to change the code itself. Reliable Data Handling is crucial – users expect their notes to be saved and loaded correctly every time. We wanted a Responsive Real-time Experience, ensuring that collaborative changes feel immediate and natural. And while it's a simple app, we kept Scalability in Mind, architecting it in a way that could conceptually handle more users and more data without a complete rewrite. Now, as you might guess – and this is a bit of a spoiler – these implementation goals actually align very closely with the formal principles of the Twelve-Factor App. Let's quickly see it in action. Now that we\'ve seen the app, let\'s see how those implementation goals connect to the Twelve-Factor App methodology as we move into the next section.
--> 

---

<!-- _class: section-title-slide -->
## Twelve-Factor Principles 
## with Spring Boot

<p></p>
<div class="logo-container">
  <img src="images/slide11_12factor_logo.png" alt="Twelve-Factor App Logo">
  <img src="images/slide11_spring_boot_logo.svg" alt="Spring Boot Logo">
</div>

<!--
Having met our sample application and understood its basic features and initial design goals, we're now ready to transition into the core of our session: exploring 'The How.' In this chapter, we'll delve into several key Twelve-Factor App principles and see specifically how Spring Boot empowers us to implement them effectively in our applications, using our Twelve-Factor Notepad app for concrete examples.
-->

---

<!-- _class: factor-slide factor-config-slide -->
<div class="factor-bar">
  <span class="factor-bar-title"><span class="factor-number">3</span>Config</span>
  <img class="factor-bar-logo" src="images/slide11_12factor_logo.png" alt="12-Factor Logo" />
</div>

## Externalize Configuration

*   **Principle:** Store config **externally**, *not in code*.
    *   Config = Values vary per deploy (e.g., urls, keys, hostnames).
    *   *Not* internal app structure (e.g., Spring bean definitions).
*   **Why?**
    *   Security (no hardcoded credentials)
    *   Flexibility across environments (dev, stage, prod)
    *   Easy updates without redeploying code
    *   Scalability (granular control, avoids named "environments").

<!--
Let's dive into our first key principle: Factor III - Configuration. The core idea, as defined by `12factor.net`, is to **store all configuration in environment variables, and strictly not in your application code. So, what exactly is 'config' in this context? It's everything that is likely to vary between your different deployments – think development, staging, and production. This includes resource handles to databases or caches, credentials for external services like payment gateways or social media APIs, and per-deploy values such as the canonical hostname for that specific deployment. Crucially, this definition does not include internal application configuration – for instance, how your Spring beans are wired together or your application's routing rules. That kind of internal structure doesn't typically change between deploys and is best kept in the code."

Why is this so critical? Firstly, Security: Hardcoding credentials or sensitive API keys directly into your codebase is a major security risk. If your code is ever exposed, so are your secrets. A good litmus test here is: could your codebase be open-sourced at any moment without compromising any credentials? If not, your config isn't fully externalized. Secondly, Flexibility: Configuration almost always varies substantially across different deployment environments. Each might use different database URLs, API endpoints, or feature flags. Externalizing config allows you to adapt to these environments without any code changes. Thirdly, it allows for **Easy Updates**: If a database password needs to change, or an API endpoint is updated, you can modify the environment configuration without needing to rebuild and redeploy your entire application. And finally, Scalability through Granular Control: The Twelve-Factor methodology emphasizes using environment variables as individual, granular controls. This is preferable to batching configuration into broad named "environments", a common practice in some frameworks. While seemingly convenient, such grouping doesn't scale well. As you add more deploys like 'staging,' 'QA,' or individual developer setups, you end up with a complex explosion of configurations. Environment variables, managed independently for each deployment, offer a much cleaner and more scalable model. In our Twelve-Factor Notepad app, we'll see how Spring Boot makes it easy to consume such environment variables for things like database connection strings or settings for external services, which we'll cover on the next slide.
-->

---

<!-- _class: factor-slide factor-config-slide code-slide -->
<div class="factor-bar spring">
  <span class="factor-bar-title"><span class="factor-number">3</span>Config</span>
  <img class="factor-bar-logo" src="images/slide11_spring_boot_logo.svg" alt="Spring Boot Logo" />
</div>

*   **application.properties:** Default and profile-specific
    ```properties
    # src/main/resources/application-dev.properties
    server.port=8080 
    ```
*   **Environment Variables:** Spring Boot auto-binds for example: `SPRING_DATASOURCE_URL`.
*   **Access** via `@Value` or `@ConfigurationProperties` type-safe.
      ```java
        @Value("${SPRING_REDIS_URL}")
        private String redisUrlValue;
      ```
*   **Spring Cloud Config:** Centralized server to manage externalized configuration for distributed applications.

<!-- 
Following the principle of externalizing configuration, Spring Boot offers several mechanisms to help us. First, we have `application.properties` or `application.yml` files. These are typically packaged with your application and can provide default settings. Spring Boot also supports profile-specific versions like `application-dev.properties` or `application-prod.properties`. As you can see in the first example from our `application-dev.properties`, we can set things like the `server.port` or custom application flags. These files allow you to define different configurations for different environments right within your project, though sensitive data should still not be committed here.

Crucially, Spring Boot allows **Environment Variables and System Properties to override** any values defined in these files. This is key for 12-Factor compliance. For example, in our `application-prod.properties`, the `spring.datasource.url` is set to `${SPRING_DATASOURCE_URL}`. This means Spring Boot will look for an environment variable named `SPRING_DATASOURCE_URL` and use its value. This is how Heroku, for instance, injects database connection strings.

For type-safe access to these properties in your Java code, Spring Boot provides annotations like **`@Value`** and the more structured **`@ConfigurationProperties`**. The `@Value` annotation, as shown in the `RedisConfig.java` snippet, allows you to inject a specific property value directly into a field. `@ConfigurationProperties` (which we don't have a dedicated class for in this small example, but it's a common pattern) lets you map a whole tree of properties to a Plain Old Java Object (POJO), providing excellent organization and type safety for your configuration.

Finally, for more complex scenarios, especially in distributed systems, there's **Spring Cloud Config**. This provides a server that externalizes configuration for applications across all environments. It's typically backed by a Git repository, allowing for version-controlled configuration that can be updated dynamically and accessed by many microservices. We won't dive deep into this today, but it's a powerful option for larger systems needing centralized configuration management.

The key takeaway is that Spring Boot provides a clear hierarchy and multiple mechanisms to consume configuration from the environment, aligning perfectly with Factor III.
-->

---

<!-- _class: factor-slide factor-secrets-slide -->
<div class="factor-bar">
  <span class="factor-bar-title"><span class="factor-number">3</span>Config: Managing Secrets</span>
  <img class="factor-bar-logo" src="images/slide11_12factor_logo.png" alt="12-Factor Logo" />
</div>

*   **Principle:** Never hardcode secrets (API keys, passwords, tokens) in code or config files.
*   **Externalize all secrets:** Use environment variables or secret management tools.
*   **Why?**
    *   Prevent accidental exposure (e.g., in source control).
    *   Enable safe rotation and updates.
    *   Meet security and compliance requirements.

<!--
This guidance comes directly from Factor III—Config—of the Twelve-Factor methodology, which states that all configuration, including secrets, must be stored outside the codebase, typically in environment variables. The rationale is to prevent accidental exposure, enable safe rotation, and meet security and compliance needs. In our sample app, all sensitive values are injected from the environment, never stored in code or checked-in config files.
-->

---


<!-- _class: factor-slide factor-config-slide code-slide -->
<div class="factor-bar spring">
  <span class="factor-bar-title"><span class="factor-number">3</span>Config: Managing Secrets</span>
  <img class="factor-bar-logo" src="images/slide11_spring_boot_logo.svg" alt="Spring Boot Logo" />
</div>

*   **Environment Variables:** Primary and simplest method.
    ```java
    @Value("${MY_SECRET_API_KEY}")
    private String apiKey;
    ```
*   **External Secret Management Systems:**
    *   Spring Cloud Vault (HashiCorp), AWS Secrets Manager
*   **Encrypted Properties (e.g., Jasypt Spring Boot):**
    *   For encrypting values at rest in properties files. 
    *   Still need a safe place for your secret key!

<!--
Spring Boot makes it easy to follow the Twelve-Factor principle for secrets. The most direct approach is to use environment variables, which Spring can inject into your code using the `@Value` annotation. In the example shown, the value of `MY_SECRET_API_KEY` is injected from the environment or config into the `apiKey` field. This keeps secrets out of your codebase and allows for easy rotation and management. For more advanced needs, Spring Boot integrates with secret management systems like HashiCorp Vault, AWS Secrets Manager, and Azure Key Vault—often via Spring Cloud extensions. These tools provide secure storage, access control, and auditability for secrets. If you must store secrets in properties files, libraries like Jasypt allow you to encrypt those values at rest, but environment variables or secret managers are preferred. In our sample app, all sensitive values are injected from the environment, never hardcoded or checked into source control, fully aligning with Factor III of the Twelve-Factor methodology.
-->

---

<!-- _class: factor-slide factor-backing-services-slide -->
<div class="factor-bar">
  <span class="factor-bar-title"><span class="factor-number">4</span>Backing Services</span>
  <img class="factor-bar-logo" src="images/slide11_12factor_logo.png" alt="12-Factor Logo" />
</div>

## Treat as Attached Resources

*   **Definition:** Any service consumed over the network. Such as dbs, messaging, caching and other app services.
*   **Key Principle:** No code distinction between local and remote services, either are **attached resources**, accessed via locator/credentials stored in **config**.
*   **Swap Services:** Change services (e.g., local DB to cloud DB) with only config updates, no code changes.
*   **Flexible Management:** Admins can attach, detach, or scale services independently of the app code.

<!--
Next, let's look at Factor IV: Backing Services. According to `12factor.net`, a backing service is any service your application consumes over the network. This includes obvious examples like datastores (PostgreSQL, MongoDB), messaging systems (RabbitMQ, Kafka), and caching systems (Redis), but also extends to things like email services or even other internal applications exposed via APIs.

The core principle here is that your application code should make **no distinction between local and third-party services**. Whether your database is running on your local machine for development or is a managed service in the cloud, your application treats it simply as an 'attached resource'.

How does it achieve this? By accessing these services via URLs or other locator/credentials that are stored in the **configuration** – which, as we just discussed under Factor III, means environment variables. The benefits of this approach are significant: First, **Loose Coupling and Portability**. Because the application doesn't care *where* the service is, only *how to connect to it* (via config), you can swap services with incredible ease. For example, you could switch from a local MySQL database during development to a cloud-hosted Amazon RDS instance in production simply by changing a configuration variable. No code changes are needed. This also leads to **Flexibility** for system administrators. They can attach new resources, detach old ones, or scale existing backing services (like upgrading a database plan) without any need to modify or redeploy the application code itself. Our Twelve-Factor Notepad app, for instance, uses a PostgreSQL database and a Redis instance. Both are configured via environment variables, allowing us to use local instances during development and cloud-managed services in production, all without changing the application's core logic. We'll see how Spring Boot helps manage these connections on the next slide.
-->

---

<!-- _class: factor-slide factor-config-slide code-slide -->
<div class="factor-bar spring">
  <span class="factor-bar-title"><span class="factor-number">4</span>Backing Services</span>
  <img class="factor-bar-logo" src="images/slide11_spring_boot_logo.svg" alt="Spring Boot Logo" />
</div>

<!-- _class: factor-slide factor-backing-services-slide code-slide -->
## Auto-configuration

*   **Auto-configuration Magic:** Spring Boot auto-configures beans for many backing services (JDBC, JPA, Redis etc.) if they are on the classpath and connection details are in `application.properties` or environment variables.
    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    ```

<!--
So, how does Spring Boot help us treat backing services as attached resources, as per Factor IV? Let's start with one of its most powerful features: Auto-configuration. Primarily through its **Auto-configuration Magic**. If Spring Boot detects, for example, the PostgreSQL driver and Spring Data JPA on the classpath (as we've included in our `pom.xml` here), it will automatically configure a `DataSource`, an `EntityManagerFactory`, and other necessary beans for database interaction. The same principle applies if it finds the Redis starter – it sets up what's needed to connect to a Redis instance. All you typically need to do is add the starter dependency to your `pom.xml` or `build.gradle` This significantly reduces boilerplate and gets you connected to your backing services quickly. Next, we'll see how we tell Spring Boot *where* these services are.
-->

---

<!-- _class: factor-slide factor-config-slide code-slide -->
<div class="factor-bar spring">
  <span class="factor-bar-title"><span class="factor-number">4</span>Backing Services</span>
  <img class="factor-bar-logo" src="images/slide11_spring_boot_logo.svg" alt="Spring Boot Logo" />
</div>

## Auto-configuration (cont.)

*   **Configuration via Environment:** Connections for backing services use externalized config, aligning with Factor III.
    ```properties
    export SPRING_DATASOURCE_URL="jdbc:postgresql://prod-db:5432/prod_db_name"
    export SPRING_REDIS_URL="redis://prod-redis:6379"
    ```
    * Or `application-prod.properties` use `${ENV_VAR}`:
        ```properties
        spring.datasource.url=${OTHER_DATASOURCE_URL}
        spring.redis.url=${OTHER_REDIS_URL}
        ```

<!--
This slide demonstrates how Spring Boot lets us externalize connection details for backing services using environment variables. By default, Spring Boot will automatically pick up standard environment variables like SPRING_DATASOURCE_URL and SPRING_REDIS_URL—even if they're not referenced in your application.properties file.

But, if you want to use a different environment variable name, or support multiple data sources, you can explicitly reference any variable you like in your config file. For example, here we use ${OTHER_DATASOURCE_URL} and ${OTHER_REDIS_URL}. This gives you flexibility to adapt to different deployment environments or naming conventions.

This approach directly supports both Factor III (Config) and Factor IV (Backing Services) from the Twelve-Factor methodology. It allows you to swap out services or credentials without code changes, and is supported by platforms like Heroku, AWS, and Kubernetes, which inject these variables at runtime.

Finally, by never committing real credentials to source control and always supplying secrets via environment variables, you improve security and make it easier to rotate credentials as needed.
-->

---

<!-- _class: factor-slide factor-config-slide code-slide -->
<div class="factor-bar spring">
  <span class="factor-bar-title"><span class="factor-number">4</span>Backing Services</span>
  <img class="factor-bar-logo" src="images/slide11_spring_boot_logo.svg" alt="Spring Boot Logo" />
</div>

<!-- _class: factor-slide factor-backing-services-slide code-slide -->
## Spring Data Abstraction

*   **Spring Data:** Provides a consistent, high-level abstraction for data access, reducing boilerplate code for common operations.
    *   Example `NoteRepository` for JPA:
        ```java
        public interface NoteRepository extends JpaRepository<Note, String> {
            // Custom query methods can be added here if needed,
            // e.g., findByColor(String color);
            // Spring Data JPA implements basic CRUD out-of-the-box.
        }
        ```

<!--
Spring Data further simplifies interaction with backing services by providing consistent, high-level abstractions. For relational databases, Spring Data JPA allows you to define a repository interface, like our `NoteRepository` here, by simply extending `JpaRepository`. Spring Data then automatically implements common CRUD operations (Create, Read, Update, Delete) for your `Note` entity. You only need to define methods for more complex custom queries Similarly, for services like Redis, Spring Data Redis offers `RedisTemplate` for direct operations or repository-style abstractions, making it easier to work with your data store without getting bogged down in low-level connection and data handling code.
-->

---

<!-- _class: factor-slide factor-config-slide code-slide -->
<div class="factor-bar spring">
  <span class="factor-bar-title"><span class="factor-number">4</span>Backing Services</span>
  <img class="factor-bar-logo" src="images/slide11_spring_boot_logo.svg" alt="Spring Boot Logo" />
</div>

<!-- _class: factor-slide factor-backing-services-slide -->
## Connection Pooling

*   **Connection Pooling:** Spring Boot handles this automatically for JDBC connections.
    *   Default, **HikariCP**, highly performant pool.
    *   No manual config is typically needed for basic pooling.
    *   Fine-tuning  via `application.properties` if specific needs arise e.g. `spring.datasource.hikari.maximum-pool-size`.

<!--
Finally, a crucial aspect of using backing services, especially databases, is **Connection Pooling**. Opening and closing database connections is expensive. Spring Boot handles this automatically for JDBC connections. It defaults to **HikariCP**, which is a very fast and efficient connection pool. This means your application will maintain a pool of ready-to-use database connections, significantly improving performance by avoiding the overhead of establishing a new connection for every database interaction. In most cases, you don't need to configure anything manually; Spring Boot's defaults for HikariCP are sensible. However, if you need to fine-tune aspects like the maximum pool size or connection timeout, you can do so through properties in your `application.properties` file. So, in essence, Spring Boot's auto-configuration, robust support for externalized configuration, high-level abstractions like Spring Data, and automatic connection pooling make adhering to the 'Backing Services as Attached Resources' principle incredibly straightforward.
-->

---

<!-- _class: factor-slide factor-build-release-run-slide -->
<div class="factor-bar">
  <span class="factor-bar-title"><span class="factor-number">5</span>Build, Release, Run</span>
  <img class="factor-bar-logo" src="images/slide11_12factor_logo.png" alt="12-Factor Logo" />
</div>

## Strict Separation

*   **Ship it already!** Demo #2
*   **Build Stage:** Code → Executable artifact (e.g., JAR/WAR). No environment-specific config.
*   **Release Stage:** Combine build artifact with config for a specific environment. Consider a release id.
*   **Run Stage:** Execute the release in the target environment.
*   **Why?**
    *   Reliable, repeatable deployments.
    *   Easier rollbacks & debugging and CI/CD (inc. config)

<!--
The Build, Release, Run principle requires a strict separation between these three stages. The build stage produces a deployable artifact from your code, with no environment-specific configuration baked in. The release stage combines this artifact with the configuration for a specific environment—such as database URLs or API keys—creating a unique release. The run stage is where the release is executed in the target environment, using the injected config. This separation ensures that builds are repeatable and reliable, and that you can promote the same artifact through multiple environments (dev, staging, prod) simply by changing the config. It also makes rollbacks and debugging much easier. In our sample app, we use Maven to build a JAR, inject config at release/run time, and can deploy the same artifact to any environment, fully aligned with this principle.
-->

---


<!-- _class: factor-slide factor-config-slide code-slide -->
<div class="factor-bar spring">
  <span class="factor-bar-title"><span class="factor-number">5</span>Build, Release, Run</span>
  <img class="factor-bar-logo" src="images/slide11_spring_boot_logo.svg" alt="Spring Boot Logo" />
</div>

<!-- _class: factor-slide factor-build-release-run-slide -->

*   **Build:** Use Maven/Gradle to create a JAR/WAR (`mvn package`).
*   **Release:** Package artifact in a Docker image (optional), config supplied at runtime.
*   **Run:** Deploy the same artifact to any environment (dev, staging, prod).
*   **CI/CD:** Automate with GitHub Actions, Jenkins, etc.
*   **Sample App:** Build with Maven, config injected at run time, deploy anywhere.

<!--
Spring Boot makes it easy to follow the Build, Release, Run principle. We use Maven or Gradle to build a deployable JAR or WAR file, with no environment-specific config included. For the release stage, we can package this artifact in a Docker image, or simply deploy the JAR as-is. Configuration—such as database URLs or API keys—is always supplied at runtime, never baked into the build. The run stage is where we execute the release in the target environment, using the injected config. This means we can promote the same artifact through dev, staging, and production, ensuring consistency and reliability. Our sample app uses Maven for builds, can be containerized with Docker, and receives its config at runtime, so it can be deployed to any environment with confidence. CI/CD pipelines like GitHub Actions or Jenkins automate these steps for repeatable, hands-off deployments.
-->

---

<!-- _class: factor-slide factor-processes-slide -->
<div class="factor-bar">
  <span class="factor-bar-title"><span class="factor-number">6</span>Processes</span>
  <img class="factor-bar-logo" src="images/slide11_12factor_logo.png" alt="12-Factor Logo" />
</div>

## Execute as Stateless Processes

*   **Principle:** Run as one or more stateless, independent processes.
    *   "Share-nothing"—no local or shared state.
    *   Persistent data lives in **backing services** (DB, Redis, etc).
*   **Why?**
    *   Enables horizontal scaling.
    *   Improves resilience and recovery.

<!--
The 'Processes' factor in the Twelve-Factor methodology emphasizes that applications should run as one or more stateless processes. This means each process is independent and does not rely on shared memory or local state. Any data that needs to persist—such as user sessions, files, or application state—should be stored in a backing service like a database or cache, not in the process itself. This approach enables true horizontal scalability: you can add or remove instances at will, and the system remains robust because no single process is critical for holding state. If a process fails, it can be replaced instantly without data loss. In our sample app, all persistent data (notes, sessions) is stored in backing services, so any app instance can be started, stopped, or replaced at any time.
-->

---


<!-- _class: factor-slide factor-config-slide code-slide -->
<div class="factor-bar spring">
  <span class="factor-bar-title"><span class="factor-number">6</span>Processes</span>
  <img class="factor-bar-logo" src="images/slide11_spring_boot_logo.svg" alt="Spring Boot Logo" />
</div>

<!-- _class: factor-slide factor-processes-slide -->

*   **Stateless by default:** REST APIs and controllers do not store user/session state in memory.
*   **Session state (if needed):** Use external stores (e.g., Redis, JDBC) via Spring Session.
    *   Our sample app uses Redis for real-time collaboration and can be configured for session storage.
*   **Avoid:** Storing critical data in local memory or on disk—use backing services.
*   **Demo:** Demo #2 statless scaled services!

<!--
Spring Boot applications are generally stateless by design, especially when building REST APIs. This means each request is handled independently, and no user or session state is kept in memory between requests. When session state is required—for example, for user authentication or collaborative features—Spring Boot makes it easy to externalize that state using Spring Session. In our sample app, Redis is used for real-time collaboration and can also be configured to store session data, ensuring that all state is managed outside the application process. This approach allows you to scale your app horizontally, add or remove instances at will, and recover quickly from failures, since no critical data is lost if a process is restarted or replaced. The key takeaway: always use backing services for persistent or shared state, never rely on in-memory or local storage.
-->

---

<!-- _class: factor-slide factor-disposability-slide -->
<div class="factor-bar">
  <span class="factor-bar-title"><span class="factor-number">9</span>Disposability</span>
  <img class="factor-bar-logo" src="images/slide11_12factor_logo.png" alt="12-Factor Logo" />
</div>

## Fast Startup, Graceful Shutdown

*   **Principle:** Processes are disposable—start and stop quickly, no reliance on local state.
*   **Why?**
    *   Enables elastic scaling and rapid deployments.
    *   Robustness: failed instances can be replaced instantly.
    *   Supports cloud-native and orchestrated environments.

<!--
The Disposability principle means that application processes should be able to start up and shut down quickly and gracefully, without relying on any local state. This is essential for modern cloud-native environments, where applications are frequently scaled up or down, restarted, or replaced by orchestrators like Kubernetes. Fast startup and graceful shutdown enable rapid deployments, elastic scaling, and robust recovery from failures. If a process crashes or needs to be replaced, it can be done instantly, with no risk of data loss or inconsistent state. In our sample app, we ensure that all state is stored in backing services, so any instance can be started, stopped, or replaced at any time, fully aligned with this principle."
-->

---


<!-- _class: factor-slide factor-config-slide code-slide -->
<div class="factor-bar spring">
  <span class="factor-bar-title"><span class="factor-number">9</span>Disposability</span>
  <img class="factor-bar-logo" src="images/slide11_spring_boot_logo.svg" alt="Spring Boot Logo" />
</div>

<!-- _class: factor-slide factor-disposability-slide -->

*   **Fast Startup:** Spring Boot optimized for quick startup; Spring Native (GraalVM) for even faster boot.
*   **Graceful Shutdown:** Actuator `/actuator/shutdown` endpoint, handles in-flight requests.
*   **Health Probes:** Actuator `/actuator/health` for liveness/readiness (Kubernetes, cloud platforms).
*   **Included in NotesApp App:**
    ```xml
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
    ```

<!--
Spring Boot provides several features that make applications highly disposable. Fast startup is a core design goal, and with Spring Native and GraalVM, you can achieve even faster boot times for cloud-native deployments. Graceful shutdown is supported out of the box via the Actuator `/actuator/shutdown` endpoint, which ensures that in-flight requests are handled before the process exits. Health probes, such as `/actuator/health`, are essential for orchestrators like Kubernetes to determine when an app is ready to receive traffic or needs to be restarted.

Enabling Actuator in your Spring Boot app is as simple as adding the `spring-boot-starter-actuator` dependency to your `pom.xml` and exposing the relevant endpoints in your `application.properties` file, as shown here. In our sample app, Actuator is enabled, making it easy to integrate with cloud platforms and orchestrators, and ensuring that any instance can be started, stopped, or replaced at any time.
-->

---

<!-- _class: factor-slide factor-dev-prod-parity-slide -->
<div class="factor-bar">
  <span class="factor-bar-title"><span class="factor-number">10</span>Dev/Prod Parity</span>
  <img class="factor-bar-logo" src="images/slide11_12factor_logo.png" alt="12-Factor Logo" />
</div>

## Keep Environments Similar

*   **Principle:** Minimize dev, staging, & prod differences.
    *   Remove Gaps: Time, Personnel, Tools (same types & versions).
*   **Why?**
    *   Avoids "it works on my machine" surprises.
    *   Yields reliable releases, faster debugging.
    *   Enables CI/CD for **continuous deployment**.

<!--
We now arrive at Factor X: Dev/Prod Parity. The guiding principle here, directly from `12factor.net`, is to 'Keep development, staging, and production as similar as possible.'. Historically, significant gaps existed between how a developer worked locally and how the application ran in production. The Twelve-Factor App methodology aims to shrink these gaps to facilitate **continuous deployment**.

The official documentation highlights three key gaps we need to bridge:
1.  **The Time Gap:** In traditional models, code might take days, weeks, or even months to reach production. A twelve-factor app aims to reduce this to hours or minutes.
2.  **The Personnel Gap:** Traditionally, developers wrote code, and a separate operations team deployed it. With dev/prod parity, developers are closely involved in deploying their code and observing its behavior in production.
3.  **The Tools Gap:** This is a major one. Developers might use one set of tools (like Nginx, SQLite on OS X), while production runs on something entirely different (like Apache, MySQL on Linux). Factor X insists on keeping the operating systems, backing services (same type AND version – e.g., Postgres 14 in dev if it's Postgres 14 in prod), and other dependencies as identical as possible across all environments.

Why is this so important?
Primarily, it helps **eliminate those frustrating 'it works on my machine' surprises.** When environments differ, you introduce unknown variables that can cause your application to behave differently in production than it did in development or staging.
This leads to **faster, more reliable debugging and releases.** If an issue does arise, it's easier to pinpoint because the environment isn't a contributing factor to the discrepancy.
It's also **crucial for robust Continuous Integration and Continuous Delivery (CI/CD)**. Automation relies on consistency.
And critically, as `12factor.net` emphasizes, it helps **avoid subtle, hard-to-trace bugs caused by differing backing service versions or behaviors**, even when using adapters that theoretically abstract differences. The cost of these issues over an application's lifetime is substantial.

Modern tools like containerization (Docker), virtual environments (Vagrant), and sophisticated packaging systems make achieving this parity far more feasible than it once was. The goal is to make your development environment a near-perfect replica of production.
-->

---


<!-- _class: factor-slide factor-config-slide code-slide -->
<div class="factor-bar spring">
  <span class="factor-bar-title"><span class="factor-number">10</span>Dev/Prod Parity</span>
  <img class="factor-bar-logo" src="images/slide11_spring_boot_logo.svg" alt="Spring Boot Logo" />
</div>

<!-- _class: factor-slide factor-dev-prod-parity-slide code-slide -->

*   **Config via `SPRING_PROFILES_ACTIVE`**
    ```properties
    server.port=8080        # application-dev.properties
    server.port=${PORT}     # application-prod.properties
    ```
*   **Embedded Servers:** Consistent runtime (Tomcat, Jetty).
*   **Containerization (CNB):** OCI images from soruce - **Demo #4**!
    ```sh
    pack build java-spring-12factor --builder heroku/builder:24
    docker run --rm -t0 -p 8080:8080 --env-file .env -e PORT=8080        
      -e SPRING_PROFILES_ACTIVE=prod java-spring-12factor:latest
    ```
*   **Spring Boot Test:** Comprehensive testing utilities.
    *   Including running tests under different profiles.

<!-- 
So, how does Spring Boot help us achieve Dev/Prod Parity, as outlined in Factor X?

First, **Spring Profiles** are a cornerstone. By using `application-{profile}.properties` or YAML files, we can define distinct configurations for different environments like 'dev', 'test', or 'prod'. This allows us to easily switch settings – such as server ports, logging levels, or even which beans are active – simply by activating a different profile. For example, our `application-dev.properties` might set a debug logging level and a specific port, while `application-prod.properties` uses a different port and expects crucial settings like database URLs to be injected from the environment, perfectly aligning with Factor III for configuration.

Second, Spring Boot applications typically use **Embedded Servers** (like Tomcat, Jetty, or Undertow) by default. These are packaged directly within your executable JAR. This means the runtime environment for your web application is consistent wherever you deploy that JAR – your local machine, a CI server, or a production instance. No more discrepancies from deploying to an externally managed application server with its own configuration quirks.

Third, **Containerization, especially with Docker,** is a game-changer for dev/prod parity. We can package our Spring Boot application (the executable JAR created by Maven or Gradle) along with all its dependencies, including a specific Java version, into a Docker image. This image becomes a consistent, immutable deployment artifact. As the snippet of a simple `Dockerfile` shows, we copy the JAR and define how to run it. You build this image once and can then run that exact same image in development, staging, and production. This drastically reduces the 'tools gap' by ensuring the application runs in an identical environment everywhere. Configuration, like `SPRING_PROFILES_ACTIVE=prod`, can then be injected into the container at runtime via environment variables.

Finally, **Spring Boot Test** provides comprehensive testing utilities that encourage writing tests that run in an environment closely resembling production. You can easily load specific Spring profiles for your tests, mock backing services, or even spin up test containers for true integration testing against real database versions, further reducing the gap between your testing environment and production.

By leveraging these features, Spring Boot significantly simplifies the task of keeping your development and production environments as similar as possible, which is key for building reliable, continuously deployable applications.
-->

---

<!-- _class: factor-slide factor-logs-slide -->
<div class="factor-bar">
  <span class="factor-bar-title"><span class="factor-number">11</span>Logs</span>
  <img class="factor-bar-logo" src="images/slide11_12factor_logo.png" alt="12-Factor Logo" />
</div>

## Treat as Event Streams

*   **Principle:** Applications should **not** concern themselves with routing or storing their output logs.
*   **Logs are event streams:** Each running process writes its event stream, unbuffered, to `stdout`.
*   **No log files:** The app never writes or manages log files.
*   **Log management is external:** The execution environment captures, aggregates, and routes logs (e.g., to files, log management systems, or dashboards).

<!--
The Eleventh Factor, **Logs**, is all about treating logs as event streams. According to [12factor.net](https://12factor.net/logs), your application should never attempt to manage log files or concern itself with log storage or routing. Instead, the app simply writes its event stream—typically as lines of text—to standard output (`stdout`). This approach decouples the application from any particular log management solution, making it portable and cloud-native.

The responsibility for capturing, storing, and analyzing logs falls to the execution environment—whether that's a local dev machine, a PaaS like Heroku, or a Kubernetes cluster. These platforms can then aggregate logs, forward them to log management systems, or display them in dashboards. This principle enables powerful features like real-time log tailing, centralized log analysis, and seamless integration with monitoring tools, all without changing application code.

In our sample app, you'll see that we rely on the platform and Spring Boot's defaults to emit logs to the console, leaving log aggregation and analysis to the environment.
-->

---


<!-- _class: factor-slide factor-config-slide code-slide -->
<div class="factor-bar spring">
  <span class="factor-bar-title"><span class="factor-number">11</span>Logs</span>
  <img class="factor-bar-logo" src="images/slide11_spring_boot_logo.svg" alt="Spring Boot Logo" />
</div>

<!-- _class: factor-slide factor-logs-slide code-slide -->

*   **Default Behavior:** Spring Boot logs to the console (stdout).
*   **No log file management:** By default, no log files are created.
*   **Structured logging:** Integration with log systems inc OTEL.
*   **Customizable:** Use `logback-spring.xml` for advanced config.
    ```xml
    <appender name="STDOUT" 
      class="ch.qos.logback.core.ConsoleAppender">
         <encoder>
             <pattern>
              %d{yyyy-MM-dd HH:mm:ss} 
              %-5level 
              %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    ```

<!--
Spring Boot is designed to align with the Twelve-Factor principle for logs right out of the box. By default, all logs are sent to the console (stdout) using Logback, and the application does not create or manage log files itself. This means your app is immediately compatible with cloud platforms, container orchestrators, and log aggregation tools that expect logs on stdout or stderr. If you need structured logging (for example, JSON output for tools like ELK or Datadog), Spring Boot makes it easy to enable with a simple configuration change or dependency. For advanced needs, you can provide a custom `logback-spring.xml` file, but the key point is that log routing, storage, and analysis are always handled externally by the environment—not by the app itself. In our sample app, we rely on this default behavior, making it easy to view logs locally, in CI, or in production, and to integrate with any log management solution you choose."
-->

---

<!-- _class: recap-slide -->
# Key Takeaways

* **Twelve-Factor Simplifies:** Embracing these principles leads to more robust, scalable, and maintainable applications.
* **Spring Boot is Your Ally:** Many features inherently support these principles (embedded servers, profiles, auto-config).
* **Start Small:** Identify 2-3 factors that offer the most immediate value to your project.
* **Leverage Spring Boot & Spring Cloud:** Use built-in capabilities and explore Spring Cloud for distributed patterns.
* **Iterative Improvement:** This is an ongoing journey!

<!--
So, as we wrap up, what are the core practical strategies for you, as Spring Boot developers, to take away from this session?

The first big takeaway is that the Twelve-Factor App methodology isn't about adding unnecessary complexity. It's actually about *simplifying* the way we design, build, and operate applications, making them more robust, easier to scale, and maintain in the long run.

We've seen how Spring Boot naturally aligns with many of these principles. Key focus areas like externalizing configuration, ensuring your application processes are stateless, striving for dev/prod parity, treating backing services like databases and message queues as attached resources, and ensuring your app is disposable with good logging practices are all well-supported. Spring Boot's embedded servers, profiles, auto-configuration, and Actuator endpoints are your friends here.

When it comes to actually implementing these in your projects:
Don't feel you have to tackle all twelve factors at once. Start small. Pick two or three that will address your most pressing challenges or provide the biggest benefits to your current application.
Actively leverage what Spring Boot already gives you. And if you're working in a distributed environment, explore how Spring Cloud components like Config Server or service discovery can further support these patterns.
Finally, remember that adopting Twelve-Factor is an iterative journey, not a destination you reach overnight. It's about continuous improvement and fostering a shared understanding within your team about *why* these practices are important.
-->

---

<!-- _class: kubecon-cta-slide -->

<p class="github-link">github.com/heroku/12factor</p>

<img src="images/slide32_methodology_icon.png" alt="QR Code for heroku/12factor GitHub" class="qr-code-logo main-qr-code">

<div class="final-logos bottom-logos">
  <img src="images/slide32_community_icon.png" alt="Community Icon">
  <img src="images/slide32_heroku_banner.png" alt="Heroku Banner">
</div>

<!--
The primary place to explore the Twelve-Factor App principles, their origins, and ongoing discussions is the `heroku/12factor` repository on GitHub. You can visit the link directly, or scan the QR code on the screen to go straight to the repository. We encourage you to explore it. Understanding the roots and the ongoing dialogue provides deeper insights as you apply these principles and perhaps even contribute back.
-->

---

<!-- _class: kubecon-cta-slide -->
# 12 Factor Notes App and More

<img src="images/12factor-notepad-app.png" alt="QR Code for Sample App GitHub Repository" class="slide33-qr-code">

<!--
Scan this QR code to visit the GitHub repository for the "Twelve-Factor Notepad" sample application we discussed today. In the README of that repository, you'll find all the further resources and links that were previously on this slide, including links to 12factor.net, Spring Boot guides, the Heroku 12Factor GitHub, and more. This way, all the information is in one convenient place for you to explore after the session.
-->

---

<!-- _class: kubecon-cta-slide qa-slide -->
# Q&A

<div class="thank-you-contact">
  <p>Thank You!</p>
  <p>Contact: afawcett@salesforce.com</p>
  <p>LinkedIn: https://www.linkedin.com/in/andyfawcett/</p>
</div>

<!-- Speaker Notes:
We have some time for questions now. Feel free to ask anything about the Twelve-Factor App, Spring Boot, Heroku, or the sample application.
-->
