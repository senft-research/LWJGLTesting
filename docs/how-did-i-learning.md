# How did I start learning OpenGL?
So these logs are intended to be insight into my thought processes whilst learning OpenGL. To that end, the only way
this will benefit readers is if I am completely honest throughout. With this in mind, I will be blunt...

I had no idea where to start! 

## Qualified, keen, but clueless...
I honestly mean it, even with a first-class masters degree, over a decade of coding experience, and being deeply rooted in
academia as a lecturer, I genuinely had no idea where to begin learning OpenGL.

There are some reading this who may think "but Jordan, there are plenty of resources online, tonnes of videos on the
subject" etc. However, this is an aspect I feel that a lot of people who try and learn new things skip, finding **good**
learning material.

## Are you bad at learning, or is content bad at teaching?
There is a truth about learning that most people do not like to admit. An idea that, when we struggle to understand a
concept, we seldom like to think.

_"What if it isn't me misunderstanding? What if it is the material isn't clear?"_

I have seen many students struggle to learn new concepts. Frequently, these students end up blaming themselves. It breaks my heart whenever I hear something 
similar to _"I am bad at math"_ or _"I am bad at CAD"_. These phrases are fallacies. One common thing I 
see with students is getting frustrated at Autodesk Inventor. _"I just don't get it"_ is a phrase I have heard often. 
However, one of Inventor's most significant issues is that it does not follow its own rules. For example, when using a 
sketch tool in Inventor, the cursor icon will change, showing clearly to the user that "a tool is being used". However, 
when one uses the dimension tool specifically, the cursor icon does not change. This leads to new learners of the 
software to learn a behaviour (that tools cause the cursor to change) to almost immediatly encounter a tool that does not
follow this rule (as setting dimensions naturally comes after drawing your first sktech). 

Users do not even realise a lot of the time that it is this inconsistency that is at the heart of their frustration, all
they think is "I do not understand / I am bad at CAD". 

Was it truly the student to blame though? Or was it that the CAD Software itself was not optimal at teaching the
user behaviour? This is, in my opinon as an educator, one of the most important habbits that learners need to try and
kick. Defaulting to the idea that it is yourself not understanding, as opposed to the content not teaching adequately, is
the easiest way for you to convince yourself out of learning something.

## What makes material great for learning?

So it is all well and good stating that material **can** be good or bad, but what are the telltale signs that help us
figure out which content is useful, and which content is detrimental to our learning?

Below are 2 examples, one "bad" source of learning content, and one "good" source of learning content.

### LWJGL Website, a lesson in why narrative cohesion matters
I do not tend to actually "read" a source from the context of learning it on my first skim through. What I tend to do is 
read it quickly, establish a context, and see if it makes _"narrative sense"_. That is to say, does it actually make 
sense to present the content in the order provided. 

For example, when starting to learn OpenGL, I knew I wanted to use LWJGL, so I went to 
[the starting page on the official website](https://www.lwjgl.org/guide). I did not actually read the
content, but rather the headings and getting a rough idea of what the page was showing me, in order:

- It shows how to download LWJGL as a link
- It then presents code showing the bare minimum setup for LWJGL (this is as deep as I go, I do not even deeply
look at the code yet)
- Then it shows how to build LWJGl from source... wait... it does what?...
- Then it has a section saying if LWJGL is actually for you or not which includes a list of other APIs you should probably learn before hand...

Can you see the issue? It shows you how to download LWJGL, how to use the code, which most learners would dive into and
copy-paste, as the guide already had you downloading it. It is only at this point does it pause and go "Hold on, do you
actually know what you need to for learning LWJGL?"

This is where I abandoned the official LWJGL website, and started looking elsewhere. If learning material starts by answering 
questions in the wrong order, it would be naive to think that the rest of the material would not have simmilar issues. 

That said, advantages do come out of this. Even though it is a "bad" source that I would not rely on, it did give me a 
heading: GLFW, OpenGL, and OpenAL. I knew already I wanted to learn OpenGL, but now had other words I could look into
when looking at other content. Specifically GLFW (as OpenAL is just an audio library, so is not what I am looking for in
this instance).

### LearnOpenGL, a great start to a hard topic

After a quick skim on the OpenGL website at their official documentation (which was as verbose and unhelpful as you could
imagine, turns out people who write low level APIs are not great at explaining things simply), I came across [LearnOpenGL](https://learnopengl.com/).
I saw that the getting started with the following sections: 
- OpenGL
- Creating a Window
- Hello Window
- Hello Triangle

This was already a good sign, as it followed an intutive order: What is the concept, how to make a window, full example
of a window, how to make a triangle. So I decided to read up to "Hello Triangle" (once again a skim read) and decide if
the learning resource was for me. 

I had decided by the second subsection of the "Creating a Window" page. Why? **It explained what GLFW was before using it!**.
This was key for me, as not only did it make narrative sense, but it also explained it clearly and concisely. But, even though my heart was
set, I did keep reading and, ultimately, things started to click into place. 

## Context is Key
Context is key...if any of my current / former students are reading this, they have likely had flashbacks to the countless lectures where
I have used this phrase, almost religiously. It is a mantra so deeply embedded into my teaching / learning philosophy, 
that I have had students quote it in dissertation acknowledgements to myself haha. But why? Well, to be blunt, because 
it is true! Context allows us to learn things much easier than if we just blindly follow the steps of tutorials, guides,
or even textbooks. And it is context in which fully prepared me to learn OpenGL / LWJGL.

When I was looking in the "Creating a Window" section of the LearnOpenGL content, I realised something. The code I was 
looking at was familliar. Even though it was C++, I had seen it somewhere before. Specifically lines like `glfwInit();`
and `GLFWwindow* window = glfwCreateWindow();`. It was then I realised where, the LWJGL getting started example. Comparing
the two together side-by-side (baring in mind, I had not started to actually code myself yet) it finally dawned on me 
what LWJGL was actually doing / its main purpose. All LWJGL was doing was providing an abstraction layer, in Java, for 
GLFW code used in C++. 

So, with that context in mind I had the following:

- A source for the Java-side OpenGL code (the bad LWJGL site) 
- A great website for learning OpenGL in C++
- An understanding that LWJGL just uses the C++ code, almost verbatim, but in Java instead

Between those 3 things I realised, I could use the LearnOpenGL site to learn, and the LWJGL site whenever the C++ code
did not transfer over to java perfectly apples-to-apples. So there we were, I downloaded LWJGL, I favourite both the LWJGL
getting started page with the code on, along with the LearnOpenGL page (and printed off the PDF version of the website
by section as I read them) and begun my journey learning OpenGL. 

This section was probably a lot more involved than you were expecting, we already several sections / tangents in, and I 
have not even started to type my first line of code yet. But, I hope with this in mind, that you take some benefit from this. 
Specifically,decisions made in this crucial phase can seriously impact your ability to understand / retain the knowledge
you are attempting to learn. 

