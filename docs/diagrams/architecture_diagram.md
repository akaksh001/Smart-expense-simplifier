```mermaid
architecture-beta
    group app(App)
    
    service main(Console Main) in app
    service gui(MainFrame GUI) in app
    service model(Model / Controller) in app
    service utils(Utilities) in app
    
    main:r --> model:l
    gui:r --> model:l
    
    model:b --> utils:t
```
