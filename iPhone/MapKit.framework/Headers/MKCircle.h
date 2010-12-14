//
//  MKCircle.h
//  MapKit
//
//  Copyright 2010 Apple, Inc. All rights reserved.
//

#if __IPHONE_4_0 <= __IPHONE_OS_VERSION_MAX_ALLOWED

#import <MapKit/MKShape.h>
#import <MapKit/MKOverlay.h>
#import <MapKit/MKGeometry.h>

@interface MKCircle : MKShape <MKOverlay> {
@package
    CLLocationCoordinate2D _coordinate;
    CLLocationDistance _radius;
    
    MKMapRect _boundingMapRect;
}

+ (MKCircle *)circleWithCenterCoordinate:(CLLocationCoordinate2D)coord
                                  radius:(CLLocationDistance)radius;

+ (MKCircle *)circleWithMapRect:(MKMapRect)mapRect; // radius will be determined from MAX(width, height)

@property (nonatomic, readonly) CLLocationCoordinate2D coordinate;
@property (nonatomic, readonly) CLLocationDistance radius;

@property (nonatomic, readonly) MKMapRect boundingMapRect; 

@end

#endif
