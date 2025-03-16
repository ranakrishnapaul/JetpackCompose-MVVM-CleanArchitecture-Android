## JetpackCompose-MVVM-CleanArchitecture-Android

This is a sample project that presents a modern approach to Android app development.

The project tries to combine popular Android tools and to demonstrate best development practices by utilizing up to date tech stack like Jetpack Compose, Kotlin Flow and Hilt(DI), MVVM, Retrofit, SOLID Principles, OOPs.

The sample app layers its presentation through MVVM Clean Architecture design pattern.


## Complete diagram and Process 
        
               View        
       
                |
   
            ViewModel     
  
                |
        
             Repository    
        
                |
        
             Retrofit      
        
                |
        
               API   

## MVVM (Model-View-ViewModel) Clean architecture 

<img width="1394" alt="Screenshot 2025-03-17 at 1 04 49 AM" src="https://github.com/user-attachments/assets/ff8663ca-54e6-4673-8a24-e6991e988507" />



## Explanation of the components:

View: The user interface (UI) layer responsible for displaying the product listing screen. It interacts with the ViewModel to fetch the proudct related data.

ViewModel: Acts as an intermediary between the View and the Repository. It exposes the proudct data as LiveData or StateFlow for the View to observe. It receives requests from the View and communicates with the Repository to fetch the data.

Repository: Manages the data operations. It abstracts the data source (API) and provides a clean API for data access. In this case, it interacts with the Retrofit client to make API calls and fetch proudct related data.

Retrofit: A type-safe HTTP client for making network requests. It handles the network operations, such as making HTTP requests, receiving responses, and parsing JSON. It communicates with the API to fetch proudct related data.

API: The backend API that provides the proudct data. It receives requests from Retrofit and returns the proudct data as a response.

The flow of data starts from the View, which triggers a request for proudct data in the ViewModel. The ViewModel communicates with the Repository, which uses Retrofit to make an API call to fetch the data from the API. The API returns the proudct related data as a response, which is then passed back to the Repository. The Repository provides the data to the ViewModel, which updates its state and exposes the proudct data to the View. Finally, the View displays the proudct listing data to the user.

This architecture follows the MVVM design pattern, where the ViewModel separates the business logic from the UI layer, and the Repository abstracts the data operations, allowing for easier testing and maintenance. Retrofit handles the network communication, providing a convenient way to fetch data from the API.


# Output of Product listing screen:

![Output_Card](https://github.com/user-attachments/assets/0d6984e1-35c8-4966-adbd-0df20f4a45e8)




