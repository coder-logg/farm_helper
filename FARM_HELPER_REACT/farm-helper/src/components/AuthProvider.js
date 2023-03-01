import React, { createContext, useState } from 'react';

export const AuthContext = createContext({
    isAuthenticated: false,
    setAuthenticated: () => { },
});

export const AuthProvider = ({ children }) => {
    const [isAuthenticated, setAuthenticated] = useState(false);

    return (
        <AuthContext.Provider value={{ isAuthenticated, setAuthenticated }}>
            {children}
        </AuthContext.Provider>
    );
}