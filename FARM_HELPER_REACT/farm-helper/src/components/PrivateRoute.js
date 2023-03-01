import React from 'react';
import { Route, Navigate } from 'react-router-dom';

export function PrivateRoute({ element, ...rest }) {
    const isAuthenticated = localStorage.getItem('isAuthenticated');
    return (
        <Route {...rest} element={isAuthenticated ? element : <Navigate to="/login" />} />
    );
}
