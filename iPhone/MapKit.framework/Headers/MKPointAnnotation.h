//
//  MKPoint.h
//  MapKit
//
//  Copyright 2010 Apple, Inc. All rights reserved.
//

#if __IPHONE_4_0 <= __IPHONE_OS_VERSION_MAX_ALLOWED

#import <Foundation/Foundation.h>
#import <MapKit/MKShape.h>
#import <CoreLocation/CLLocation.h>

@interface MKPointAnnotation : MKShape {
@package
    CLLocationCoordinate2D _coordinate;
}

@property (nonatomic, assign) CLLocationCoordinate2D coordinate;

@end

#endif
