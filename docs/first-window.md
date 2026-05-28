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
So, when looking at the "Creating a Window" section, I realised that there were quite a few things simmilar with that of
the LWJGL website code. Because of that, whilst I did a full readthrough of the "Creating a Window" section of the website,
I did ultimately take more inspiration from the LWJGL example code block. The main reason was because a few of the methods
on the LearnOpenGL site were not exact one-to-one in LWJGL, with some custom constants and parameter types being required.
For example: //TODO example here.

Hence, the LWJGL code example was invaluable here! However, the code itself had, what I like to call "faff". That is to 
say, content within the codeblock example was there that was not needed. This is where I have to stress a point I have 
already made several times in these first few logs... "read content before you action content".

## Final Thoughts