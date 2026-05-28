# First Window vs Chucking my Laptop out the Window

##  How do I download this!?!
The first thing that needed to be done to begin learning LWJGL, was to actually download LWJGL. This ended up being a 
small headache in its own right. Specifically a headache that, reasonably easily, could put someone off learning LWJGL 
all together.

### In Maven we trust.
For those uninitiated with Java, I am a big fan of something called "Maven", which is used to set up Java projects and 
its dependencies. Specifically, how to make sure the right libraries and packages are pre-downloaded into the project 
without me having to run some specific installation commands (npm eat your heart out haha).

The issue is, I could not find for the life of me the Maven information I needed to set the LWJGL library as a dependency.
So, what did I do? Googled it of course! 

### Googling is easy... right?
So I typed into Google "How to download LWJGL using Maven" and was bombarded by forum posts, stack overflow questions, but
no definitive, easy to understand answers. Actually, I am lying, I did not type that into Google, but yet it seems like
a perfectly reasonable thing to search for nowadays right? This reveals an uncomfortable truth, one I have believed in for some 
years now... a lot of people have forgotten how to use Google properly. 

There seems to be this obsession with typing verbose, fully grammatically correct questions into google but, 
counter-intuitively, this tends to yield worse results. Do you not find it interesting that, by searching a question in
Google, you get a lot of website pages of people asking questions? The fact of the matter is that is Google working as 
intended. When you search on Google, you are searching for pages that use the same words / phrases you are searching for. 

If you search for a question in Google, you will get pages with questions on as results... What did I type then? All I 
searched for was quite plainly _"LWJGL maven dependency"_. No fanfare, no question, heck it isn't even grammatically 
correct language. What was the very first search result? The official Maven repository for LWJGL... need I say more? Well, actually... yes!

### There must be an easier way
When I started reading the Maven Repo, I noticed there were a LOT of dependencies. Like a ridiculous number. So,
rather than adding them all to my Maven configuration by hand (a tedious task, to put it lightly), I used common sense. 
I said to myself "surely someone has had this problem before?" which then naturally led to me asking "surely someone has
made a tool to make this easier?". 

Some at this point would be tempted to jump into their LLM of choice and get it to do it. I understand the temptation, 
but I also think it is a trap. Why? Because I do not know LWJGL yet, so I wouldn't know if the LLM made mistakes. When
learning new things, it is always beneficial to choose the most deterministic tools possible. Why risk an LLM getting 
things wrong when you do not have the skills yet to spot when it does? 

This is where I went back to the website for LWJGL. Google is not always the answer to the problem, sometimes you just 
have to look by hand. Within a minute I found the Download page on the site, and saw a "Customize LWJGL 3" button. It took me 
to an extensive tool that let me set up the packages I wanted, set that I wanted to use Maven, and that was that, I had
a full copy-pasteable configuration file for setting up LWJGL. 

This took all of about 5 minutes to figure out, from that first google search. No following unofficial guidance, no using
LLMs that could have got it wrong and I have no idea. Just a plain, definitive, straight from the source, set of dependencies
that I can copy-paste into my Maven config. Sometimes the simple methods are honestly the most reliable / quickest.

## LWJGL had a bad getting started page, lets use it! 
So, when looking at the "Hello Window" section, I realised that there were quite a few things simmilar with that of
the LWJGL website code. Because of that, whilst I did a full readthrough of the "Hello Window" section of the website,
I did ultimately take more inspiration from the LWJGL example code block. 

### Same concept, different implementation
The main reason for relying on the LWJGL example was because a few of the methods on the LearnOpenGL site were not exactly
one-to-one in LWJGL, with some custom constants / parameter types being required.
For example: 

- In C++ / GLFW, the method for creating the window itself is : `GLFWwindow* window = glfwCreateWindow(300, 300, "Hello World!", NULL, NULL);`

- In Java / LWJGL the method for creating a window is is `long window = glfwCreateWindow(300, 300, "Hello World!", NULL, NULL);`

Whilst almost identical, the window value is specifically returned as a `long` in java, and the `NULL` parameters are
a specific type of `MemoryUtil.NULL`, whereas this is not clarified in the C++ example as `MemoryUtil` is a specific
LWJGL type. I have to guitily admit I did not clock this initially in my first attempts to learn, and accidentally utilised
the primitive `null` in Java, and spent a solid 15 minutes scratching my head trying to figure out what I was doing wrong.

### Filtering out the Faff
Whilst already quite useful to me, the LWJGL example code has a lot of what I like to call "faff". That is to
say, content within the example was there that was not really needed for learning the basics. This is where I have to 
stress a point I have already made several times in these first few logs... "read content before you action content".

When I decided the LWJGL code would be useful, I gave it a full read as I was building my "hello window" code example. 
What I noticed was that there were several parts of the code that were not really present in the LearnOpenGL content I 
was reading in paralell. Specifically, this segment caught my eye:

```java
glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
});
```

I took a few minutes looking up the documentation for this method and, between my previous knowledge and the docs I found,
it was clear to me this was demonstrating recording keystrokes to run logic. To clarify, this piece of code checks if the escape 
key has been pressed and, if it had, it would close the window. 

Whilst cool to now know thats how inputs are handled in LWJGL / GLFW, it was quite useless to me at this point. I had 
not even begun learning how to render a triangle yet. Why would I care in the slightest about how to do keyboard event
logic? 

So what did I do? Ignored it of course. In my version of "hello window" I did not bother putting this into the code. The 
ability to close the window via the escape key is trivial, and not required in the slightest. When learning core concepts
of something new, I try quite hard to ensure as little of this "faff" is present as possible. I want to be able to wake up
the next day, come back to my "hello window" example code, and not have to spend 20 minutes figuring out what was important.

My "hello window" code was for one purpose only. Figure out how to make a window pop up on screen. That was it, everything
else just confuses matters. I dont care how to do keyboard inputs, I don't care how to reposition the window on screen (yet), 
and many other tiny bits of "faff". So I simply do not add them. This would have been impossible if I had "actioned content
as I read it", as I would have never had that processing time to recognise it as the faff it was. 

## The issues
I will be the first to admit, I am an idiot. That is to say, even as a lecturer, even as someone with a fancy degree, my
ability to make mistakes has always been consistent. I feel too many tutorials / guides pretend the process of learning is
one of perfection. Seldom do I see mistakes honestly mentioned by tutorials / guides, especially in the modern day. That 
said, here are 2 of the big mistakes / headaches I encountered during making my "hello window" example work.

### Not that NULL, this NULL!

I have already mentioned how I used `null` originally in the `glfwCreateWindow` method, but I also thought that
`GLFW_PLATFORM_NULL` was the correct Enum to use in the method, and indeed the IDE / compiler had no issues with this. 
However, it was not the correct parameter, and LWJGL, in all its wisdom, doesn't have any general "sanity checks" to
warn the user about this, in my opinion, generally common mistake. In fact, even in the LWJGL code example, the author uses
`NULL` rather than `MemoryUtil.NULL` making the issue more difficult to sport.

### Why won't you run!?
When I was first setting up the main logic for the window, I kept getting an error:
`FATAL ERROR in native method: Thread[main,5,main]: No context is current or a function that is not available in the current context was called. The JVM will abort execution.`

The error itself was quite irksome, as when looking it up online there were many reasons this could happen. It took me 15 
minutes to figure out a line of code from the LWJGL example, `GL.createCapabilities();`, was required to be ran before any
of the general GLFW / OpenGL methods could be, or else this error pops up. Essentially the capabilities create the context
that the error message is complaining about.

This did teach me a valuable lesson though "the debug messages for LWJGL / OpenGL were not going to help me in a straight
forward way".



## Final Thoughts
I hope throughout this log you can appreciate just how much "prep work" I do before I fully begin to learn something new.
If I had charged in head first, I would have likely bounced off of LWJGL quite quickly. Between the download issues, the 
confusing  differences between LWJGL and bog-standard C++ GLFW, and the small mistakes that took way more time than I would
have liked, this process could have easily seen me waving a white flag in defeat. 

And yet, through both the prep work, and the ability to take a step back from a problem, and analyse it before trying to
resolve it straight away, you can usually find your way. Not only that, but you make your way to the end of a core concept
with a richer / in-depth understanding of what you are trying to learn. It is why I am not a big fan of utilising LLMs 
when I am learning a new skill. Yes, a lot of the time they are great at **doing** the basics. However, without encountering
the issues that LLMs help you avoid, you lose out on challenges that genuinely help you understand the content in a more
in-depth way.

To summarise? Reading the content before utilising it can save you a lot of headaches and issues. Furthermore, the issues 
that you will inevitably encounter, if you embrace them / their challenges, can end up being some of the greatest lessons 
you can be taught!