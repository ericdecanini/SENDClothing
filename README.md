# SEND. Clothing

END. Clothing is an amazing name but I felt like being original with my version of the app.

This app makes use of an MVP architecture while also making use of RxJava, Retrofit, and Dagger to show a catalog of "Test Shirts".

I do have to make some flaws apparent.

One distinction from the real app is the lack of some functions in that screen either due to a lack of data or scope, including the filter options and bottom navigation. These have been replaced with a happy little function.

As for some UI distinctions, you might notice that the colour of each product is missing. I have the TextView ready but set its visibility to gone. This is one of the pieces of data I noticed isn't present in the API. I could use a palette I figured but this was as much as I can do before the weekend.

There's also the popup menus from the Sort and View options on the toolbar. I used the regular Android popup menus instead of a custom one like in the real app, again, due to a lack of hours I was able to put into the app. If you want to give me the weekend to address both of these issues, I'm more than happy to do so.

One final flaw is in the testing. You may notice the tests I've written are very basic implementations of Espresso and Mockito. Writing tests is one of my weakest points in Android Development as I've only decided to start exploring it recently. Also, for some reason, I couldn't get Android Studio to recognise Robolectric in the test class so I don't even know if my instrumented test runs. Again, something I'm willing to solve over the weekend if you wish for me to do so.



